package com.example.iotmanager01.graph;

import android.content.Context;

import com.example.iotmanager01.TokenRepository;

public class GraphPresenterImpl implements GraphPresenter{
    GraphView graphView;
    TokenRepository token;

    GraphPresenterImpl(GraphView view, Context context){
        graphView = view;
        token = TokenRepository.getInstance(context);
    }

    @Override
    public void logout() {
        token.clear();
    }
}
