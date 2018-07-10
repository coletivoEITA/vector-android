/*
 * Copyright 2018 Cooperativa EITA (eita.org.br), Vinicius Cubas Brand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package im.vector.cloud;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;

import org.matrix.androidsdk.util.JsonUtils;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Class used to make requests to the cloud external server.
 */
public class CloudRestClient {

    // http client
    private OkHttpClient mOkHttpClient = new OkHttpClient();

    protected Gson gson;

    protected CloudService mApi;

    public CloudRestClient(String serverUrl) {
        // The JSON -> object mapper
        gson = JsonUtils.getGson(false);

        // Rest adapter for turning API interfaces into actual REST-calling objects
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mOkHttpClient)
                .build();

        mApi = adapter.create(CloudService.class);
    }

    public void getRoomSharedFolders(String roomId, String userId, Callback<List<CloudFolder>> callback) {
        try {
            mApi.roomSharedFolders(roomId, userId, callback);
        } catch (Throwable t) {

        }
    }
}
