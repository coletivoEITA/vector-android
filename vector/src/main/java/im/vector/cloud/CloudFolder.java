/*
 * Copyright 2018 Cooperativa EITA (eita.org.br), Vinicius Cubas Brand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.cloud;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import im.vector.rios.Dir;
import tellh.com.recyclertreeview_lib.TreeNode;

public class CloudFolder {
    public int id;
    public String name;
    public boolean isSelected = false;
    public List<CloudFolder> children;

    public TreeNode<Dir> toTreeNode(CloudFolder selected) {
        TreeNode<Dir> ret = new TreeNode<>(new Dir(this.name,this.id));
        if (this.id == selected.id) {
            ret.getContent().isSelected = true;
        }

        for (CloudFolder child: children) {
            TreeNode<Dir> childNode = child.toTreeNode(selected);
            ret.addChild(childNode);
            if (selected != null && selected.id == child.id) {
                childNode.getContent().isSelected = true;
                TreeNode<Dir> parent = ret;
                while (parent != null) {
                    if (!parent.isExpand()) {
                        parent.toggle();
                    }
                    parent = parent.getParent();
                }
            }
        }
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof CloudFolder && ((CloudFolder) obj).id == this.id;
    }
}
