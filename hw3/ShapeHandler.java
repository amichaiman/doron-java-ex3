package hw3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ShapeHandler {
	private static Scanner sc;

	public static void main (String[] args) {
		PrismPyramid[] arr = new PrismPyramid[Integer.valueOf(args[1])];
		List<PrismPyramid> list = new LinkedList<>();
		Map<Double, List<PrismPyramid>> map = new TreeMap<>();
        try {
            fill(arr,list,map,args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        ((TreeMap<Double, List<PrismPyramid>>) map).firstEntry();
//        ((TreeMap<Double, List<PrismPyramid>>) map).lastEntry();
        try {
            PrintWriter printWriter = new PrintWriter("shapesOutArr.txt", "UTF-8");
            List<PrismPyramid> arrayAsList = Arrays.asList(arr);
            Collections.reverse(arrayAsList);
            for (PrismPyramid p : (PrismPyramid[]) arrayAsList.toArray()) {
                if (p != null) printWriter.println(p);
            }
            printWriter.close();
            printWriter = new PrintWriter("shapesOutList.txt", "UTF-8");
            for (PrismPyramid p : list) {
                printWriter.println(p);
            }
            printWriter.close();
            printWriter = new PrintWriter("shapesOutMap.txt", "UTF-8");
            for (List<PrismPyramid> maplist : map.values()) {
                for (PrismPyramid p : maplist) {
                    printWriter.println(p);
                }
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

	private static void fill(PrismPyramid[]arr, List<PrismPyramid> list,Map<Double,List<PrismPyramid>> map, String fileName) throws FileNotFoundException {
        sc = new Scanner(new File("./src/hw3/"+fileName));
		String line ="";
		int i=0;
		PrismPyramid polygon = null;
		while(sc.hasNext()) {
			line =sc.nextLine();
            try {
                polygon = construct(line);
                arr[i++]=polygon;
                list.add(0,polygon);
                updateMap(map, polygon);
            } catch (InputLineException e) {
                System.out.println("The following line is invalid: " + line);
            }
		}
		List<PrismPyramid> arrayAsList = Arrays.asList(arr);
		Collections.reverse(arrayAsList);
		arr = (PrismPyramid[]) arrayAsList.toArray();
        System.out.println(arr[20]);
	}

    private static void updateMap(Map<Double,List<PrismPyramid>> map, PrismPyramid polygon) {
	    List<PrismPyramid> tmp = map.get(polygon.getHeight());
	    if (tmp == null) {
	        tmp = new LinkedList<>();
	        tmp.add(polygon);
	        map.put(polygon.getHeight(), tmp);
        } else {
	        tmp.add(polygon);
        }
    }

    private static PrismPyramid construct(String line) throws InputLineException {
		PrismPyramid polygon = null;
		Scanner sc = new Scanner (line);
		String type, base, params;
		sc.useDelimiter("#");
		type=sc.next();
		if (type.charAt(0) != 'y' && type.charAt(0) != 'r') {
            sc.close();
		    throw new InputLineException("Invalid type");
        }
		base=sc.next();
        if (type.charAt(0) != 'y' && type.charAt(0) != 'r') {
            sc.close();
            throw new InputLineException("Invalid type");
        }
        try {
            params = sc.next();
        } catch (Exception e) {
            throw new InputLineException("Invalid input");
        }

		Scanner baseScan =new Scanner (base);
		baseScan.useDelimiter(",");
		Scanner paramsScan =new Scanner (params);
		paramsScan.useDelimiter(",");

        RegularPolygon baseShape;
        try {
            int n = baseScan.nextInt();
            Point center = new Point(baseScan.nextDouble(), baseScan.nextDouble());
            Point vertex = new Point(baseScan.nextDouble(), baseScan.nextDouble());
            baseShape = new RegularPolygon(center, n, vertex);
        } catch (Exception e) {
            sc.close();
            paramsScan.close();
            baseScan.close();
            throw new InputLineException("invalid base input");
        }
		if (type.equals("y")) {
            try {
                polygon = new Pyramid(baseShape, paramsScan.nextDouble(), paramsScan.nextDouble(), paramsScan.nextDouble(), paramsScan.nextDouble());
            } catch (Exception e) {
                sc.close();
                paramsScan.close();
                baseScan.close();
                throw new InputLineException("invalid pyramid values");
            }

		}
		else if(type.equals("r")) {
            try {
                polygon = new Prism(baseShape, paramsScan.nextDouble(), paramsScan.nextDouble());
            } catch (Exception e) {
                sc.close();
                paramsScan.close();
                baseScan.close();
                throw new InputLineException("invalid prism values");
            }
		}

        sc.close();
        paramsScan.close();
        baseScan.close();
		return polygon;
	}
}
