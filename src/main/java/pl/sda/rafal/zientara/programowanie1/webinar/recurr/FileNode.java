package pl.sda.rafal.zientara.programowanie1.webinar.recurr;

import java.util.List;

public class FileNode {

    private FileNode parent;
    private List<FileNode> nodes;
    private boolean expandable;
    private boolean isOpened = false;
    private String name;

    public FileNode(FileNode parent,
                    List<FileNode> nodes,
                    boolean expandable,
                    String name) {
        this.parent = parent;
        this.nodes = nodes;
        this.expandable = expandable;
        this.name = name;
    }

    public int getLevel() {
        int level = 0;
        FileNode currentParent = parent;
        while (currentParent != null) {
            level++;
            currentParent = currentParent.parent;
        }
        return level;
    }

    public void toggleOpen() {
        isOpened = !isOpened;
    }

    public String getName() {
        return name;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public List<FileNode> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
//        int level = 0;
        int level = getLevel();
        for (int i = 0; i < level; i++) {
            builder.append("_");
        }
        if (nodes == null) {
            builder.append("   ");
        } else {
            if (isOpened) {
                builder.append("\\/ ");
            } else {
                builder.append(" > ");
            }
        }
        builder.append(" ").append(name);
        return builder.toString();

    }
}
