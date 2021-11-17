package com.mbhutto.gui;

import com.mbhutto.util.Constants;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.Result;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DepartmentDisplay extends JPanel
{
    JFrame parent   ;
    JPanel tablePanel;
    JPanel buttonPanel;
    String deptTableColumnNames[];
    JTable deptTable;
    JScrollPane deptTableScroll;

    JButton refreshButton;
    JButton addButton;
    JButton viewButton;
    JButton updateButton;
    JButton deleteButton;
    private DepartmentFieldsView departmentFieldsView ;
    private Department selectDepartments;
    private Result departmentsResult;


    public DepartmentDisplay(JFrame parent) throws SQLException, ClassNotFoundException
    {
        setLayout(new BorderLayout());
        // Data Table
        this.parent = parent ;
        deptTableColumnNames = new String[5];
        deptTable = new JTable();
        loadDepartmentsFromDatabase();
        deptTableScroll = new JScrollPane(deptTable);
        deptTable.setFillsViewportHeight(true);
        deptTableScroll.setPreferredSize(new Dimension(1300, 600));

        // BUTTONS
        refreshButton   = new JButton("Refresh Departments");
        addButton       = new JButton("Add New Department");
        viewButton      = new JButton("View Department");
        updateButton    = new JButton("Update Department");
        deleteButton    = new JButton("Delete Department");

        // TABLE/CENTER PANEL
        tablePanel = new JPanel(); // for deptTable
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Department Table", TitledBorder.CENTER, TitledBorder.TOP));
        this.add(tablePanel, BorderLayout.CENTER);
        tablePanel.add(deptTableScroll);

        // BUTTONS/SOUTH PANEL
        buttonPanel = new JPanel(); // for buttons
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // ACTION LISTENER FOR refreshButton
        refreshButton.addActionListener(e -> {
            try {
                loadDepartmentsFromDatabase();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        // ACTION LISTENER FOR addButton
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                departmentFieldsView = new DepartmentFieldsView(parent, "Add Department", true, Constants.ADD_VIEW, null);
            }
        });

        // ACTION LISTENER FOR viewButton
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++) {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                departmentFieldsView = new DepartmentFieldsView(parent, "View Department", true,Constants.DISPLAY_VIEW, department);
            }
        });


        // ACTION LISTENER FOR updateButton
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++) {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                departmentFieldsView = new DepartmentFieldsView(parent, "Update Department", true,Constants.UPDATE_VIEW, department);
            }
        });

        // ACTION LISTENER FOR deleteButton
        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++)
                {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                int decision = JOptionPane.showConfirmDialog(tablePanel, "Are you sure you want to delete this item?");
                if (decision == 0)
                {
                    try
                    {
                        int deletedRows = department.deleteRecord();
                        System.out.println(deletedRows);
                        if (deletedRows > 0)
                        {
                            JOptionPane.showMessageDialog(tablePanel, "Department deleted Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                            boolean recordFound = false;
                            String[][] tempRecords = new String[(departmentsResult.getRecords().length - 1)][5];
                            for (int i = 0; i < departmentsResult.getRecords().length; i++)
                            {
                                if (department.getDepartmentName() == departmentsResult.getRecords()[i][1])
                                {
                                    recordFound = true;
                                    continue;
                                }

                                for (int j = 0; j < departmentsResult.getRecords()[i].length; j++)
                                {
                                    if (!recordFound)
                                    {
                                        tempRecords[i][j] = departmentsResult.getRecords()[i][j];
                                    } else
                                    {
                                        tempRecords[i - 1][j] = departmentsResult.getRecords()[i][j];
                                    }
                                }
                            }
                            departmentsResult.setRecords( new String[tempRecords.length][5]);
                            for (int i = 0; i < tempRecords.length; i++)
                            {
                                departmentsResult.getRecords()[i][0] = tempRecords[i][0];
                                departmentsResult.getRecords()[i][1] = tempRecords[i][1];
                                departmentsResult.getRecords()[i][2] = tempRecords[i][2];
                                departmentsResult.getRecords()[i][3] = tempRecords[i][3];
                                departmentsResult.getRecords()[i][4] = tempRecords[i][4];
                                System.out.println(departmentsResult.getRecords()[i][1]);
                            }

                            loadDepartments(departmentsResult.getRecords());

                        }

                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }


            }

        });
    }

    private void loadDepartmentsFromDatabase() throws SQLException, ClassNotFoundException {
        selectDepartments = new Department();
        departmentsResult = selectDepartments.getRecords();
        loadDepartments(departmentsResult.getRecords());
    }

    private void loadDepartments(String[][] records)
    {
        DefaultTableModel model = (DefaultTableModel) deptTable.getModel();
        deptTableColumnNames = new String[]{"ID", "Depatrment Name", "Short Name", "Terms", "Sections"};
        String[][] data = records;
        model.setDataVector(data, deptTableColumnNames);
    }
}