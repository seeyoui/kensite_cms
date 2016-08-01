package com.seeyoui.kensite.common.base.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.StringUtils;

/**
 * 
 * @author cuichen
 */
public class TreeJson implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String pid;
	private String text;
	private String iconCls;
	private String state;
	private boolean checked;
	private Attributes attributes = new Attributes();
	private List<TreeJson> children = new ArrayList<TreeJson>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		if(StringConstant.TRUE.equals(checked)) {
			this.checked = true;
		}
		if(StringConstant.FALSE.equals(checked)) {
			this.checked = false;
		}
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public List<TreeJson> getChildren() {
		return children;
	}

	public void setChildren(List<TreeJson> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeJson other = (TreeJson) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}

	public static void getTree(List<TreeJson> list, TreeJson root) {
		for(int i=0; i<list.size(); i++) {
			TreeJson tj = list.get(i);
			if(StringUtils.isNotBlank(tj.getPid()) && tj.getPid().equals(root.getId())) {
				if(root.getChildren().indexOf(tj) == -1) {
					root.getChildren().add(tj);
				}
				list.remove(tj);
				i--;
			}
		}
		for(int i=0; i<root.getChildren().size(); i++) {
			getTree(list, root.getChildren().get(i));
		}
	}
}
