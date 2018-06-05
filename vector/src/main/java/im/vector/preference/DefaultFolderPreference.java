package im.vector.preference;

import android.content.Context;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.matrix.androidsdk.rest.model.RiosFolder;

import java.util.List;

public class DefaultFolderPreference extends ListPreference {

    private List<RiosFolder> folderList;

    private RiosFolder selectedFolder;

    public DefaultFolderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultFolderPreference(Context context) {
        super(context);
    }

    public void setFolderList(List<RiosFolder> folderList) {
        this.folderList = folderList;
    }

    public RiosFolder getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(RiosFolder selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    @Override
    protected View onCreateDialogView() {
        ListView view = new ListView(getContext());
        view.setAdapter(adapter());
        setEntries(entries());
        setEntryValues(entryValues());
        setValueIndex(getCurrentIndex());
        /*
        setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {

                return true;
            }
        });*/

        return view;
    }

    private ListAdapter adapter() {
        return new ArrayAdapter(getContext(), android.R.layout.select_dialog_singlechoice);
    }

    private CharSequence[] entries() {
        CharSequence strs[] = new CharSequence[folderList.size()+1];
        strs[0] = "/";
        RiosFolder folder;
        for (int i=0; i<folderList.size();i++) {
            folder = folderList.get(i);
            strs[i+1] = folder.name;
        }
        return strs;
    }

    private CharSequence[] entryValues() {
        CharSequence strs[] = new CharSequence[folderList.size()+1];
        RiosFolder folder;
        strs[0] = "";
        for (int i=0; i<folderList.size();i++) {
            folder = folderList.get(i);
            strs[i+1] = folder.id.toString();
        }
        return strs;
    }

    private Integer getCurrentIndex() {
        RiosFolder folder;
        if (selectedFolder == null) {
            return 0;
        }

        for (int i=0; i<folderList.size();i++) {
            folder = folderList.get(i);
            if (folder.id == selectedFolder.id) {
                return i;
            }
        }
        return 0;
    }

}
