package com.data.paging

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.data.entity.BreedEntity
import com.data.local.AppDatabase
import com.data.preferences.PreferencesManager
import com.data.remote.TheCatApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

/**
 * Created by Thanh Quang on 16/07/2022.
 */
@OptIn(ExperimentalPagingApi::class)
class BreedRemoteMediator(
    private val theCatApi: TheCatApi,
    private val appDatabase: AppDatabase,
    private val preferencesManager: PreferencesManager
) : RemoteMediator<Int, BreedEntity>() {
    private val breedItemDao = appDatabase.breedItemDao()

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BreedEntity>
    ): MediatorResult {
        return try {
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.

            val loadKey = when (loadType) {
                LoadType.REFRESH -> {

                    preferencesManager.prefRemoteKey = 1
                    preferencesManager.prefRemoteKey
                }
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    if (preferencesManager.prefRemoteKey == null || preferencesManager.prefRemoteKey == 0) {
                        preferencesManager.prefRemoteKey = 1
                    }
                    preferencesManager.prefRemoteKey
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = theCatApi.getBreedsAsync(
                attachBreed = 0,
                page = loadKey ?: 0,
                limit = 10
            ).await()

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    breedItemDao.deleteAllBreeds()
                    preferencesManager.prefRemoteKey = 1
                }
                preferencesManager.prefRemoteKey = loadKey?.plus(1)
                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.


                for (i in response.indices) {
                    coroutineScope {
                        val bytes = getByteImageFromURL(response[i].image?.url).await()
//                        response[i].image_blob = bytes
                    }
                }
                breedItemDao.insertBreeds(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}

fun CoroutineScope.getByteImageFromURL(url: String?): Deferred<ByteArray?> = async {
    val bmp = loadBitmap(url).await()
    if (bmp != null) {
        val stream = ByteArrayOutputStream()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            bmp.compress(Bitmap.CompressFormat.WEBP_LOSSY, 10, stream)
        } else {
            bmp.compress(Bitmap.CompressFormat.WEBP, 10, stream)
        }
        bmp.recycle()
        return@async stream.toByteArray()
    }
    null
}

fun CoroutineScope.loadBitmap(url: String?): Deferred<Bitmap?> = async {
    var bm: Bitmap? = null
    var `is`: InputStream? = null
    var bis: BufferedInputStream? = null
    try {
        val conn: URLConnection = URL(url).openConnection()
        conn.connect()
        `is` = conn.getInputStream()
        bis = BufferedInputStream(`is`, 8192)
        bm = BitmapFactory.decodeStream(bis)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (bis != null) {
            try {
                bis.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (`is` != null) {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    bm
}