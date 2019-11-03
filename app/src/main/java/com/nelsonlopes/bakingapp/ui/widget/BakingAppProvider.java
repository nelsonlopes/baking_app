package com.nelsonlopes.bakingapp.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppProvider extends AppWidgetProvider {

    public static List<Ingredient> mIngredients;

    public BakingAppProvider()
    {

    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetIds[], List<Ingredient> ingredients)
    {
        mIngredients = ingredients;
        for (int appWidgetId : appWidgetIds)
        {
            Intent intent = new Intent(context, ListViewService.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
            views.setRemoteAdapter(R.id.list_view_widget, intent);
            ComponentName component = new ComponentName(context, BakingAppProvider.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_view_widget);
            appWidgetManager.updateAppWidget(component, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    @Override
    public void onEnabled(Context context)
    {

    }

    @Override
    public void onDisabled(Context context)
    {

    }
}

