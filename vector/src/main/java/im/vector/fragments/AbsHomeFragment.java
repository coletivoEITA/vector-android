package im.vector.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import org.matrix.androidsdk.util.Log;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import im.vector.R;
import im.vector.activity.VectorHomeActivity;

/**
 * Abstract fragment providing the universal search
 */
public abstract class AbsHomeFragment extends Fragment {

    private static final String LOG_TAG = AbsHomeFragment.class.getSimpleName();

    protected VectorHomeActivity mActivity;

    // Butterknife unbinder
    private Unbinder mUnBinder;

    private String mCurrentFilter;

    /*
     * *********************************************************************************************
     * Fragment lifecycle
     * *********************************************************************************************
     */

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
    }

    @Override
    @CallSuper
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mActivity = (VectorHomeActivity) getActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_action_mark_all_as_read:
                Log.e(LOG_TAG, "onOptionsItemSelected mark all as read");
                onMarkAllAsRead();
                return true;
        }
        return false;
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();

        mCurrentFilter = null;
    }

    @Override
    @CallSuper
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    /*
     * *********************************************************************************************
     * Public methods
     * *********************************************************************************************
     */

    /**
     * Apply the filter
     *
     * @param pattern
     */
    public void applyFilter(final String pattern) {
        if (TextUtils.isEmpty(pattern)) {
            if (mCurrentFilter != null) {
                onResetFilter();
            }
        } else if (!TextUtils.equals(mCurrentFilter, pattern)) {
            onFilter(pattern, new OnFilterListener() {
                @Override
                public void onFilterDone(int nbItems) {
                    mCurrentFilter = pattern;
                }
            });
        }
    }

    /*
     * *********************************************************************************************
     * Abstract methods
     * *********************************************************************************************
     */

    protected abstract void onMarkAllAsRead();

    protected abstract void onFilter(final String pattern, final OnFilterListener listener);

    protected abstract void onResetFilter();

    /*
     * *********************************************************************************************
     * Listener
     * *********************************************************************************************
     */

    public interface OnFilterListener {
        void onFilterDone(final int nbItems);
    }
}
