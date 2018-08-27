package im.vector.rios;

import im.vector.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class RFile implements LayoutItemType {
    public String fileName;

    public RFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.rios_item_file;
    }
}
