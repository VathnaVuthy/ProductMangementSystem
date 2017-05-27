package Java;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by User on 5/19/2017.
 */
public class StockSystem {
    int mRows = 5;
    int pageNumber = 1;
    int totalPages;
    Scanner scanner = new Scanner(System.in);
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    public ArrayList<Product> products = new ArrayList<>();

    CellStyle allignRight = new CellStyle(CellStyle.HorizontalAlign.right);
    CellStyle allignLeft = new CellStyle(CellStyle.HorizontalAlign.left);
    CellStyle allignCenter = new CellStyle(CellStyle.HorizontalAlign.center);

    public static void main(String args[]) {
        String choice;
        Scanner scanner = new Scanner(System.in);
        StockSystem main = new StockSystem();

        //Test Initialize Product;

        main.products.add(new Product(1, "Coke", 12, 50, main.date));
        main.products.add(new Product(2, "Pepsi", 13, 40, main.date));
        main.products.add(new Product(3, "Mama", 12, 50, main.date));
        main.products.add(new Product(4, "Sting", 12, 50, main.date));
        main.products.add(new Product(5, "Water", 12, 50, main.date));
        main.products.add(new Product(6, "Paper", 12, 50, main.date));
        main.products.add(new Product(7, "Books", 12, 50, main.date));
        main.products.add(new Product(8, "Pencil", 12, 50, main.date));


        do {
            main.setHeader();
            System.out.print("Option > ");
            choice = scanner.next();
            switch (choice) {
                case "*":
                    main.Display();
                    break;

                case "W":
                case "w":
                    main.Write();
                    break;

                case "U":
                case "u":
                    main.Update();
                    break;

                case "D":
                case "d":
                    int beforeDelete = main.products.size();
                    main.Delete();
                    if(beforeDelete == main.products.size());
                    System.out.println("Product Not Found");
                    break;

                case "r":
                case "R":
                    main.Read();
                    break;

                case "s":
                case "S":
                    main.Search();
                    break;

                case "se":
                case "Se":
                    main.SetRow();
                    break;

                case "N":
                case "n":
                    main.NextPage();
                    break;

                case "P":
                case "p":
                    main.PreviousPage();
                    break;

                case "g":
                case "G":
                    System.out.println("Pages: ");
                    int p = scanner.nextInt();
                    if(p > main.totalPages || p < 0){
                        System.out.println("Page Not Found");
                    }else{
                        main.pageNumber = p;
                        main.Goto(p);
                    }

                    break;


                case "F":
                case "f":
                    main.Display();
                    break;

                case "L":
                case "l":
                    main.Last(main.totalPages);
                    break;
            }
        } while (true);
    }

    public void setHeader() {
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(1, BorderStyle.DESIGN_TUBES, ShownBorders.SURROUND);
        table.addCell("", cellStyle);
        table.addCell("  *)Display | W)rite | R)ead | U)pdate | D)elete | F)ist | P)revious | N)ext | L)ast  ", cellStyle);
        table.addCell("", cellStyle);
        table.addCell("S)earch | G)o to | Se)t row | Sa)ve | B)ackup | Re)sore | H)elp | E)xit", cellStyle);
        System.out.println(table.render());
        System.out.println();

    }

    public void Display() {
        totalPages = (int) Math.ceil((products.size() / (float) mRows));
        Table table = new Table(5, BorderStyle.DESIGN_TUBES, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        pageNumber = 1;
        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 15, 20);
        table.setColumnWidth(2, 10, 15);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 12);
        table.addCell(" ID ", allignCenter);
        table.addCell(" Name ", allignCenter);
        table.addCell(" Unit Price ", allignCenter);
        table.addCell(" Stock Quantity ", allignCenter);
        table.addCell(" Import Date ", allignCenter);
        for (int i = 0; i < mRows; i++) {
            Product product = products.get(i);
            table.addCell(" " + product.getId() + "");
            table.addCell(" " + product.getName() + "");
            table.addCell("$" + product.getUnitPrice() + " ", allignRight);
            table.addCell(" " + product.getStockQuantity() + " ", allignRight);
            table.addCell("" + df.format(product.getImportedDate()), allignCenter);
        }
        table.addCell("Page: " + pageNumber + "/" + totalPages, allignCenter, 2);
        table.addCell("Total Record: " + products.size(), allignCenter, 4);
        System.out.println(table.render());
    }

    public void Write() {
        int lastId;
        Product product = products.get(products.size() - 1);
        lastId = product.getId();
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Unit Price: ");
        double up = scanner.nextDouble();
        System.out.println("Stock Quantity: ");
        int sq = scanner.nextInt();
        products.add(new Product(lastId + 1, name, up, sq, date));
    }

    public void Update() {
        System.out.println("Input ID: ");
        int ID = scanner.nextInt();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (ID == product.getId()) {
                System.out.println("Name: ");
                String name = scanner.next();
                product.setName(name);
                System.out.println("Unit Price: ");
                double up = scanner.nextDouble();
                product.setUnitPrice(up);
                System.out.println("Stock Quantity: ");
                int sq = scanner.nextInt();
                product.setStockQuantity(sq);
                System.out.println("Date");
                String Date = scanner.next();
                Date date;
                try {
                    date = df.parse(Date);
                    product.setImportedDate(date);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    public void Delete() {
        System.out.println("Input ID");
        int ID = scanner.nextInt();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (ID == product.getId()) {
                products.remove(i);
                System.out.println("Product has been remove successfully");
                Display();
            }
        }
    }

    public void Read() {
        System.out.print("Input ID: ");
        int ID = scanner.nextInt();
        System.out.println();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == ID) {
                System.out.println("Detail");
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Unit Price: " + product.getUnitPrice());
                System.out.println("Quantity in Stock: " + product.getStockQuantity());
                System.out.println("Imported Date: " + df.format(product.getImportedDate()));
            }
        }
    }

    public void Search() {
        System.out.println("Input Product's Name: ");
        String ProductName = scanner.next();
        System.out.println();
        CellStyle allignRight = new CellStyle(CellStyle.HorizontalAlign.right);
        CellStyle allignLeft = new CellStyle(CellStyle.HorizontalAlign.left);
        CellStyle allignCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(5, BorderStyle.DESIGN_TUBES, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 15, 20);
        table.setColumnWidth(2, 10, 15);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 12);
        table.addCell(" ID ", allignCenter);
        table.addCell(" Name ", allignCenter);
        table.addCell(" Unit Price ", allignCenter);
        table.addCell(" Stock Quantity ", allignCenter);
        table.addCell(" Import Date ", allignCenter);
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if ((product.getName().toLowerCase()).equals(ProductName.toLowerCase())) {
                table.addCell(" " + product.getId() + "");
                table.addCell(" " + product.getName() + "");
                table.addCell("$" + product.getUnitPrice() + " ", allignRight);
                table.addCell(" " + product.getStockQuantity() + " ", allignRight);
                table.addCell("" + df.format(product.getImportedDate()), allignCenter);
            }
        }
        System.out.println(table.render());
    }

    public void SetRow() {
        System.out.print("ROW: ");
        mRows = scanner.nextInt();
        System.out.println("\n Rows Has been changed!");
    }

    public void NextPage() {
        Table table = new Table(5, BorderStyle.DESIGN_TUBES, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 15, 20);
        table.setColumnWidth(2, 10, 15);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 12);
        table.addCell(" ID ", allignCenter);
        table.addCell(" Name ", allignCenter);
        table.addCell(" Unit Price ", allignCenter);
        table.addCell(" Stock Quantity ", allignCenter);
        table.addCell(" Import Date ", allignCenter);
        pageNumber++;
        if (pageNumber <= totalPages)
            Goto(pageNumber);
        else {
            Goto(totalPages);
        }
    }

    public void PreviousPage() {
        Table table = new Table(5, BorderStyle.DESIGN_TUBES, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 15, 20);
        table.setColumnWidth(2, 10, 15);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 12);
        table.addCell(" ID ", allignCenter);
        table.addCell(" Name ", allignCenter);
        table.addCell(" Unit Price ", allignCenter);
        table.addCell(" Stock Quantity ", allignCenter);
        table.addCell(" Import Date ", allignCenter);
        pageNumber--;
        if (pageNumber <= 1)
            pageNumber = 1;
        Goto(pageNumber);
    }

    public void Goto(int pageNO) {
        Table table = new Table(5, BorderStyle.DESIGN_TUBES, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 15, 20);
        table.setColumnWidth(2, 10, 15);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 12);
        table.addCell(" ID ", allignCenter);
        table.addCell(" Name ", allignCenter);
        table.addCell(" Unit Price ", allignCenter);
        table.addCell(" Stock Quantity ", allignCenter);
        table.addCell(" Import Date ", allignCenter);
        for (int i = (pageNO - 1) * mRows; i < (pageNO * mRows); i++) {
            if (pageNumber > totalPages)
                pageNumber = totalPages;
            if (i >= products.size()) break;
            Product product = products.get(i);
            table.addCell(" " + product.getId() + "");
            table.addCell(" " + product.getName() + "");
            table.addCell("$" + product.getUnitPrice() + " ", allignRight);
            table.addCell(" " + product.getStockQuantity() + " ", allignRight);
            table.addCell("" + df.format(product.getImportedDate()), allignCenter);
        }
        table.addCell("Page: " + pageNumber + "/" + totalPages, allignCenter, 2);
        table.addCell("Total Record: " + products.size(), allignCenter, 4);
        System.out.println(table.render());
    }

    public void Last(int last) {
        Table table = new Table(5, BorderStyle.DESIGN_TUBES, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 15, 20);
        table.setColumnWidth(2, 10, 15);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 12);
        table.addCell(" ID ", allignCenter);
        table.addCell(" Name ", allignCenter);
        table.addCell(" Unit Price ", allignCenter);
        table.addCell(" Stock Quantity ", allignCenter);
        table.addCell(" Import Date ", allignCenter);
        for (int i = (last - 1) * mRows; i < (last * mRows); i++) {
            if (pageNumber > totalPages)
                pageNumber = totalPages;
            if (i >= products.size()) break;
            Product product = products.get(i);
            table.addCell(" " + product.getId() + "");
            table.addCell(" " + product.getName() + "");
            table.addCell("$" + product.getUnitPrice() + " ", allignRight);
            table.addCell(" " + product.getStockQuantity() + " ", allignRight);
            table.addCell("" + df.format(product.getImportedDate()), allignCenter);
        }
        table.addCell("Page: " + totalPages + "/" + totalPages, allignCenter, 2);
        table.addCell("Total Record: " + products.size(), allignCenter, 4);
        System.out.println(table.render());
    }
}