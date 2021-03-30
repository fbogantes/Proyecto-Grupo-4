/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import gestion.EmpleadoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import model.YearGender;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
/**
 *
 * @author wmolina
 */
@Named(value = "chartAnimatedView1")
@SessionScoped
public class ChartAnimatedView1 implements Serializable {

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
         /*Declare Lines*/
        LineChartSeries boys = new LineChartSeries();
        LineChartSeries girls = new LineChartSeries();
        /*Allow fill the grafic*/
        boys.setFill(true);
        girls.setFill(true);

        /*Variables for Labels*/
        String label = "N/A";
        String label1 = "N/A";

        /*Call DataBase*/
        ArrayList<YearGender> list = EmpleadoGestion.getIngresoYearGender();
        int mayor = list.get(0).getTotal();

        /*Create a New List to get the gender*/
        ArrayList<String> genders = new ArrayList<>();

        /*Iterate existing list to add the elements to the new list*/
        list.forEach(linea -> {
            genders.add(linea.getGenero());
        });

        /*Get Collection without duplicates  Distinct  */
        List<String> distinctGenders = genders.stream().distinct().collect(Collectors.toList());

        /*Assign Labels*/
        for (String s : distinctGenders) {
            if (s.equalsIgnoreCase("M")) {
                label = "Masculino";

            }
            if (s.equalsIgnoreCase("F")) {
                label1 = "Femenino";
            }
        }

        boys.setLabel(label);
        girls.setLabel(label1);

        for (YearGender row : list) {
            if (row.getGenero().equalsIgnoreCase("M")) {
                boys.set(row.getYear(), row.getTotal());                
            }
            if (row.getGenero().equalsIgnoreCase("F")) {
                girls.set(row.getYear(), row.getTotal());
            }
            if (mayor < row.getTotal()) {
                mayor = row.getTotal();
            }
        }

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createAnimatedModels() {
//        animatedModel1 = initLinearModel();
//        animatedModel1.setTitle("Line Chart");
//        animatedModel1.setAnimate(true);
//        animatedModel1.setLegendPosition("se");
//        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(10);

        animatedModel1 .setTitle("Ingreso Year Gender");
        animatedModel1 .setLegendPosition("ne");
        animatedModel1 .setStacked(true);
        animatedModel1 .setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Years");
        animatedModel1 .getAxes().put(AxisType.X, xAxis);
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Total");
        yAxis.setMin(0);
//        yAxis.setMax(200);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Bar Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
}
