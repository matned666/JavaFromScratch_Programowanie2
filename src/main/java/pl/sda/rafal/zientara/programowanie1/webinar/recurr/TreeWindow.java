package pl.sda.rafal.zientara.programowanie1.webinar.recurr;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TreeWindow {

    private JFrame frame;
    private JList<FileNode> tree;
    private DefaultListModel<FileNode> model;
    private TreeHandler handler = new TreeHandler();
    private List<FileNode> flatNodes;

    public TreeWindow() {
        frame = new JFrame("Dzewa");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 800);

        tree = new JList<>();
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selectedIndex = tree.getSelectedIndex();
                if (selectedIndex != -1) {
                    FileNode node = flatNodes.get(selectedIndex);
                    node.toggleOpen();
                    refreshTree();
                }
            }
        });
        model = new DefaultListModel<>();
        tree.setModel(model);
        frame.add(tree);

        refreshTree();
        frame.setVisible(true);
    }

    private void refreshTree() {
        flatNodes = handler.getFlatList();

        model.clear();
        for (FileNode node : flatNodes) {
            model.addElement(node);
        }
    }
}
