package im.vector.rios;

import im.vector.R;
import im.vector.cloud.CloudFolder;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class Dir implements LayoutItemType {
    public String dirName;
    public Integer fileId;
    public Boolean isSelected = false; //Used just in the construction of the screen

    public Dir(String dirName) {
        this.dirName = dirName;
    }

    public Dir(String dirName, Integer fileId) {
        this.dirName = dirName;
        this.fileId = fileId;
    }

    public CloudFolder toCloudFolder() {
        CloudFolder cf = new CloudFolder();
        cf.name = this.dirName;
        cf.id = this.fileId;
        return cf;
    }


    @Override
    public int getLayoutId() {
        return R.layout.rios_item_dir;
    }

}
