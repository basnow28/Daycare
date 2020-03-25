package fileManagement;

import model.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FileManagement {

    //  Input From Files
    public void inputFromFile(ArrayList<Child> children, ArrayList<Parent> parents, ArrayList<Employee> employees,
                              ArrayList<Shift> shifts, ArrayList<WorkSchedule> workSchedules, String childFileName,
                              String parentFileName, String employeeFileName, String shiftFileName,
                              String workScheduleFileName) throws IOException, ParseException {

        inputChildren(children,childFileName);
        inputParents(parents,parentFileName);
        inputEmployees(employees,employeeFileName);
        inputShifts(shifts,shiftFileName);
        inputWorkSchedules(workSchedules,workScheduleFileName);
    }

    public void inputChildren(ArrayList<Child> children, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = "", firstName, lastName, cpr;
        int age, id, parentId;

        while((line = br.readLine()) != null) {
            String[] split = line.split("\\s+");

            //CHILD
            id =  Integer.parseInt(split[0]);
            firstName = split[1];
            lastName = split[2];
            age = Integer.parseInt(split[3]);
            cpr = split[4];
            parentId = Integer.parseInt(split[5]);      //it's the same as id for now

            Child child = new Child(id,firstName, lastName, age, cpr,parentId);
            children.add(child);
        }
    }

    public void inputParents(ArrayList<Parent> parents, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = "", firstName, lastName, cpr, email, phoneNumber;
        int id, childId;

        while((line = br.readLine()) != null) {
            String[] split = line.split("\\s+");

            //CHILD
            id =  Integer.parseInt(split[0]);
            firstName = split[1];
            lastName = split[2];
            cpr = split[3];
            email = split[4];
            phoneNumber = split[5];
            childId = Integer.parseInt(split[6]);      //it's the same as id for now

            Parent parent = new Parent(id,firstName, lastName, cpr, email, phoneNumber, childId);
            parents.add(parent);
        }
    }

    public void inputEmployees(ArrayList<Employee> employees, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = "", firstName, lastName, cpr, email, phoneNumber;
        int id, workingHours;
        double salary;
        EmployeeType type;

        while((line = br.readLine()) != null) {
            String[] split = line.split("\\s+");

            //CHILD
            id =  Integer.parseInt(split[0]);
            firstName = split[1];
            lastName = split[2];
            cpr = split[3];
            email = split[4];
            phoneNumber = split[5];
            type = EmployeeType.valueOf(split[6]);
            salary = Double.parseDouble(split[7]);
            workingHours = Integer.parseInt(split[8]);

            Employee employee = new Employee(id,firstName, lastName, cpr, email, phoneNumber, type, salary, workingHours);
            employees.add(employee);
        }
    }

    public void inputShifts(ArrayList<Shift> shifts, String fileName) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = "", startTime, endTime;
        ShiftType shiftType;
        EmployeeType employeeType;
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");

        while((line = br.readLine()) != null) {
            String[] split = line.split("\\s+");

            //SHIFT
            startTime = split[0];
            endTime = split[1];
            shiftType = ShiftType.valueOf(split[2]);
            employeeType = EmployeeType.valueOf(split[3]);
            date = formatter.parse(split[4]);

            Shift shift = new Shift(startTime, endTime, shiftType,employeeType,date);
            shifts.add(shift);
        }
    }

    public void inputWorkSchedules(ArrayList<WorkSchedule> workSchedules, String fileName) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = "", shifts;
        int id, employeeId;

        while((line = br.readLine()) != null) {
            String[] split = line.split("\\s+");

            //WORK SCHEDULE
            id = Integer.parseInt(split[0]);
            employeeId = Integer.parseInt(split[1]);
            shifts = split[2];

            WorkSchedule workSchedule = new WorkSchedule(id,employeeId, shifts);
            workSchedules.add(workSchedule);
        }
    }

    //Modify (in)
    public void modifyFile(String oldLine, String newLine, String fileName, ArrayList<?> arr) throws IOException   {
        String line = "";
        String oldText = "";
        BufferedReader input = new BufferedReader(new FileReader(fileName));

        int lineNr = 0;
        while((line = input.readLine())!= null)  {
            lineNr++;

            if(lineNr != arr.size()) {
                oldText += line + "\r\n";
            }  else {
                oldText += line;
            }
        }

        input.close();

        String newText = oldText.replaceAll(oldLine,newLine);

        FileWriter output = new FileWriter(fileName);
        output.write(newText);
        output.close();
    }

    //Delete (from)
    public void deleteFromFile(String lineToDelete, String fileName, ArrayList <?> arr)  throws FileNotFoundException, IOException{
        File inputFile = new File(fileName);
        File tempFile = new File("myTempFile.txt");

        BufferedReader input = new BufferedReader(new FileReader(inputFile));
        BufferedWriter output = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        int line = 0;
        while((currentLine = input.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            line++;
            if(trimmedLine.equals(lineToDelete))
                continue;
            if(line != arr.size() - 1) {
                output.write(currentLine + System.getProperty("line.separator"));
            }  else {
                output.write(currentLine);
            }
        }
        output.close();
        input.close();
        final boolean WRITABLE = inputFile.setWritable(true);
        boolean delete = inputFile.delete();
        boolean successful = tempFile.renameTo(inputFile);
    }
}
