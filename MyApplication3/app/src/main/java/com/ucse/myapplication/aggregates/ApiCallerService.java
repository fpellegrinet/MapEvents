package com.ucse.myapplication.aggregates;

import java.util.List;

public class ApiCallerService {

    private final ApiCallerClient client;

    public ApiCallerService() {
        client = new ApiCallerClient();
    }

    public List<Item> getItemsToList() {
        return client.getItems();
    }
}
