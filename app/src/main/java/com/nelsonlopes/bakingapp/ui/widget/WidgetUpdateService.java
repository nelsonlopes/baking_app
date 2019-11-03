package com.nelsonlopes.bakingapp.ui.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Ingredient;
import com.nelsonlopes.bakingapp.model.Recipe;

import java.util.List;

public class WidgetUpdateService extends IntentService {
    public static final String WIDGET_UPDATE_ACTION = "update_widget";
    private List<Ingredient> mIngredients = null;

    public WidgetUpdateService()
    {
        super("WidgetServiceUpdate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {

        if (intent != null && intent.getAction().equals(WIDGET_UPDATE_ACTION))
        {
            Recipe recipe = intent.getParcelableExtra(getString(R.string.parcel_recipe));
            mIngredients = recipe.getIngredients();

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppProvider.class));
            BakingAppProvider.updateAppWidget(this, appWidgetManager, appWidgetIds, mIngredients);
        }
    }
}
