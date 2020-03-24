package fileManagement;

import model.*;

import java.io.*;
import java.util.ArrayList;


public class FileManagement {

    //read file and data into the corresponding arrayList
    public void inputFromFile(ArrayList<Child> children, ArrayList<Parent> parents, ArrayList<Employee> employees, ArrayList<WaitingList> waitingLists,
                              String childFileName, String parentFileName, String employeeFileName, String waitingListFilename) throws IOException {

        inputChildren(children,childFileName);
        inputParents(parents,parentFileName);
        inputEmployees(employees,employeeFileName);
        inputWaitingLists(waitingLists, waitingListFilename);
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
    public void inputWaitingLists(ArrayList<WaitingList> waitingLists, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = "", year, childrenIds;
        int id, capacity;
        Quarter quarter;

        while((line = br.readLine()) != null) {
            String[] split = line.split("\\s+");

            //WAITING LIST
            id = Integer.parseInt(split[0]);
            quarter = Quarter.valueOf(split[1]);
            year = split[2];
            capacity = Integer.parseInt(split[3]);
            childrenIds = split[4];

            WaitingList waitingList = new WaitingList(id, quarter, year, capacity, childrenIds);
            waitingLists.add(waitingList);
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
