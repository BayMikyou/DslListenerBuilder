package com.mikyou.plugins.dsl.listener.builder.ui;

import com.mikyou.plugins.dsl.listener.builder.ext.ExtGUIKt;
import com.mikyou.plugins.dsl.listener.builder.ui.model.VModelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class DslListenerBuilderDialog extends JDialog {
    private static final int MAX_ITEM_COUNT = 20;
    private JButton mBtnOK;
    private JButton mBtnCancel;
    private JButton mBtnAdd;
    private JButton mBtnDelete;
    private JTextField mTFieldActionName;
    private JTextField mTFieldTypeList;
    private JPanel contentPane;
    private JList mActionListView;
    private JComboBox<String> mCBoxValueType;
    private List<VModelListener> mModelListenerList = new ArrayList<>();
    private int[] mSelectedIndexArray;
    private DialogListener mDialogListener;

    public DslListenerBuilderDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(mBtnOK);
        initView();
        initEvent();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void initView() {
        mActionListView.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        mCBoxValueType.setEditable(true);
        mBtnAdd.setEnabled(true);
        mBtnDelete.setEnabled(false);
        mModelListenerList.clear();
    }

    private void initEvent() {
        mBtnOK.addActionListener(e -> onOK());
        mBtnCancel.addActionListener(e -> onCancel());
        mBtnAdd.addActionListener(e -> addActionItem());
        mBtnDelete.addActionListener(e -> {
            if (mSelectedIndexArray.length > 0 && mSelectedIndexArray.length <= mModelListenerList.size()) {
                for (int selectedIndex : mSelectedIndexArray) {
                    if (selectedIndex >= 0 && selectedIndex <= mModelListenerList.size() - 1 && mModelListenerList.get(selectedIndex) != null) {
                        mModelListenerList.remove(selectedIndex);
                    }
                }
                refreshActionListView();
            }
        });
        mActionListView.addListSelectionListener(e -> {
            mBtnDelete.setEnabled(true);
            mSelectedIndexArray = mActionListView.getSelectedIndices();
        });

    }

    private void addActionItem() {
        String actionName = mTFieldActionName.getText();
        String paramTypeList = mTFieldTypeList.getText();
        Object selectedItem = mCBoxValueType.getSelectedItem();
        String returnValueType = (selectedItem != null) ? selectedItem.toString() : "Unit";
        if (mModelListenerList.size() < MAX_ITEM_COUNT) {
            mModelListenerList.add(new VModelListener(actionName, paramTypeList, returnValueType));
            refreshActionListView();
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "add action item can not be more than max item count", "来自DslListenerBuilder错误提示", JOptionPane.ERROR_MESSAGE);
        }

        resetInput();
    }

    private void resetInput() {
        mTFieldActionName.setText("");
        mTFieldTypeList.setText("");
        mCBoxValueType.setSelectedIndex(0);
    }

    private void refreshActionListView() {
        mActionListView.setModel(new DefaultComboBoxModel(mModelListenerList.stream().map(it ->
                String.format(Locale.US, "m%sAction: ((%s) -> %s)", it.getActionName(), it.getParamTypeList(), it.getReturnValueType())
        ).toArray()));
        mActionListView.setSelectedIndex(0);
    }


    private void onOK() {
        if (mDialogListener != null) {
            Map<String, String> map = new HashMap<>();
            for (VModelListener vModelListener : mModelListenerList) {
                map.put(vModelListener.getActionName(), String.format(Locale.US, "(%s) -> %s", vModelListener.getParamTypeList(), vModelListener.getReturnValueType()));
            }
            mDialogListener.onOkBtnClicked(map);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        DslListenerBuilderDialog dialog = new DslListenerBuilderDialog();
        ExtGUIKt.showDialog(dialog, 550, 600, true, false);
        System.exit(0);
    }

    public void setDialogListener(DialogListener listener) {
        mDialogListener = listener;
    }

    public interface DialogListener {
        void onOkBtnClicked(Map<String, String> map);

        void onCancelBtnClicked();
    }
}
