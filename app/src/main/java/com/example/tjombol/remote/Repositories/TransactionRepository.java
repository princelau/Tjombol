package com.example.tjombol.remote.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.tjombol.DB.DAOs.TransactionDao;
import com.example.tjombol.DB.Entities.TxEntity;
import com.example.tjombol.remote.ApiService;
import com.example.tjombol.remote.Models.TransactionResponse;
import com.example.tjombol.remote.NetworkBoundResource;
import com.example.tjombol.remote.Resource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

    /**
    Application application;

    private static OkHttpClient providesOkHttpClientBuilder() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build();

    }


    //private List<TransactionResponse> webserviceResponseList = new ArrayList<>();
    public LiveData<List<TxEntity>> providesWebService() {

        final MutableLiveData<List<TxEntity>> data = new MutableLiveData<>();

        //String response = "";
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpClientBuilder())
                    .build();

            //Defining retrofit api service
            ApiService apiService = retrofit.create(ApiService.class);
            //response = service.makeRequest().execute().body();

            Call<List<TxEntity>> call = apiService.getTransactions();

            call.enqueue(new Callback<TransactionResponse>() {
                @Override
                public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                    if (!response.isSuccessful()) {
                        Log.d("Tx Repository", "Code: " + response.code());
                        //textView.setText("Code: "+ response.code());
                        return;
                    }

                    Log.d("Tx Repository", "Response::::" + response.body());
                    List<TxEntity> transactions = response.body();


                    for (TxEntity transaction : transactions) {
                        String content = "";
                        content += "txId: " + transaction.getTransactionId() + "\n";
                        content += "comment: " + transaction.getComment() + "\n";
                        content += "date: " + transaction.getDate() + "\n";
                        content += "sender: " + transaction.getSender() + "\n";
                        content += "receiver: " + transaction.getReceiver() + "\n";
                        content += "txStatus: " + transaction.getTransactionStatus() + "\n";
                        content += "type: " + transaction.getType() + "\n";
                        content += "amount: " + transaction.getAmount() + "\n";
                        Log.d("Tx Repository", "Response::::" + response.body());
                    }

                    data.setValue(transactions);
                }

                @Override
                public void onFailure(Call<TransactionResponse> call, Throwable t) {
                    Log.d("Repository", "Failed:::");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        //  return retrofit.create(ResultModel.class);
        return data;

    }
}
*/
public class TransactionRepository {
    private final TransactionDao transactionDao;
    private final ApiService apiService;

    @Inject
    TransactionRepository(TransactionDao transactionDao, ApiService service) {
        this.transactionDao = transactionDao;
        this.apiService = service;
    }
    /**
     * This method fetches the transactions from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @return List of transactions
     */
    public LiveData<Resource<List<TxEntity>>> loadTransactions() {
        return new NetworkBoundResource<List<TxEntity>, TransactionResponse>() {

            @Override
            protected void saveCallResult(TransactionResponse item) {
                if(null != item)
                    transactionDao.saveTransactions(item.getTransactions());
            }

            @NonNull
            @Override
            protected LiveData<List<TxEntity>> loadFromDb() {
                return transactionDao.getAllTxs();
            }

            @NonNull
            @Override
            protected Call<TransactionResponse> createCall() {
                return apiService.getTransactions();
            }
        }.getAsLiveData();
    }
}