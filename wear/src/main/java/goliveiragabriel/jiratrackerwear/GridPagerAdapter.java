package goliveiragabriel.jiratrackerwear;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Path;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

/**
 * Created by Gabriel on 17/02/2016.
 */
public class GridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    static final int[] BG_IMAGES  = new int[] {
            android.R.color.holo_green_light,
            android.R.color.holo_blue_bright
    };
    private final Page[][] PAGES = { };


    public GridPagerAdapter(Context context, FragmentManager fm){
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        String title = page.titleRes != 0 ? mContext.getString(page.titleRes) : null;
        String text = page.textRes != 0 ? mContext.getString(page.textRes) : null;
        CardFragment fragment = CardFragment.create(title, text, page.iconRes);
        fragment.setCardGravity(page.cardGravity);
        fragment.setExpansionEnabled(true);
        fragment.setExpansionDirection(CardFragment.EXPAND_UP);
        fragment.setExpansionFactor(1.0f);
        return fragment;
    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    @Override
    public int getColumnCount(int i) {
        return PAGES[i].length;
    }

    private static class Page {
        int titleRes;
        int textRes;
        int iconRes;
        int cardGravity;

    }
}
