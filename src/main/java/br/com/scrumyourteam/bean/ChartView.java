
package br.com.scrumyourteam.bean;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;
     
    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
     
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Burndown Sprint Atual");
        lineModel1.setShowPointLabels(true);
        lineModel1.setLegendPosition("e");
        lineModel1.getAxes().put(AxisType.X,new Axis("Duração Sprint") {});
        
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Estimativa de Trabalho");
        yAxis.setMin(0);
        yAxis.setMax(10);
         
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries idealLine = new LineChartSeries();
        idealLine.setLabel("Execução Ideal");
 
        idealLine.set(1, 2);
        idealLine.set(2, 1);
        idealLine.set(3, 3);
        idealLine.set(4, 6);
        idealLine.set(5, 8);
 
        LineChartSeries realLine = new LineChartSeries();
        realLine.setLabel("Execução Real");
 
        realLine.set(1, 6);
        realLine.set(2, 3);
        realLine.set(3, 2);
        realLine.set(4, 7);
        realLine.set(5, 9);
 
        model.addSeries(idealLine);
        model.addSeries(realLine);
         
        return model;
    }
 
}