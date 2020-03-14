package pl.sda.rafal.zientara.programowanie1.webinar.recurr;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TreeHandler {

    private final FileNode root;

    public TreeHandler() {
        this(new File("C:\\DATA\\data-wyszukiwanie plikow"));
    }

    public TreeHandler(File rootFile) {
        root = getNode(rootFile, null);
        System.out.println(root);
    }

    private FileNode getNode(File file, FileNode parent) {
        List<FileNode> nodes = new ArrayList<>();
        File[] files = file.listFiles();
        String name = file.getName();
        FileNode output;
        if (files != null) {
            output = new FileNode(parent, nodes, true, name);
            for (File f : files) {
                FileNode node = getNode(f, output);
                nodes.add(node);
            }
        } else {
            output = new FileNode(parent, null, false, name);
        }
        return output;
    }

    public List<FileNode> getFlatList() {
        List<FileNode> nodes = new LinkedList<>();
        addNodes(nodes, root);
        return nodes;
    }

    private void addNodes(List<FileNode> nodes, FileNode node) {
        nodes.add(node);
        if (node.isExpandable() && node.isOpened()) {
            List<FileNode> fileNodes = node.getNodes();
            for (FileNode subNode : fileNodes) {
                addNodes(nodes, subNode);
            }
        }
    }
}
