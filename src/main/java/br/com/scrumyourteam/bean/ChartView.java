
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
 
    private LineChartModel teamChart;
    private LineChartModel individualChart;
     
    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    public LineChartModel getTeamChart() {
        return teamChart;
    }
 
    public LineChartModel getIndividualChart() {
        return individualChart;
    }
     
    private void createLineModels() {
        teamChart = initLinearModel();
        teamChart.setTitle("Burndown Sprint Atual");
        teamChart.setShowPointLabels(true);
        teamChart.setLegendPosition("e");
        teamChart.getAxes().put(AxisType.X,new Axis("Duração Sprint") {});
        
        Axis yAxis = teamChart.getAxis(AxisType.Y);
        yAxis.setLabel("Estimativa de Trabalho");
        yAxis.setMin(0);
        yAxis.setMax(10);
        
        
        individualChart = initLinearModel();
        individualChart.setTitle("Burndown Sprint Atual");
        individualChart.setShowPointLabels(true);
        individualChart.setLegendPosition("e");
        individualChart.getAxes().put(AxisType.X,new Axis("Duração Sprint") {});
        
        yAxis = individualChart.getAxis(AxisType.Y);
        yAxis.setLabel("Estimativa de Trabalho");
        yAxis.setMin(0);
        yAxis.setMax(10);
         
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries idealLine = new LineChartSeries();
        idealLine.setLabel("Execução Ideal");
 
        idealLine.set(1, 2);//ter dois pontos, max e min
        idealLine.set(2, 1);//trazer max do banco
        idealLine.set(3, 3);//data inicial sprint e soma estimativa
        idealLine.set(4, 6);
        idealLine.set(5, 8);
 
        LineChartSeries realLine = new LineChartSeries();
        realLine.setLabel("Execução Real");
 
        realLine.set(1, 6);//pegar sempre ultima data da tarefa
        realLine.set(2, 3);//se for diferente de done soma
        realLine.set(3, 2);//trazer resultados para cada dia
        realLine.set(4, 7);
        realLine.set(5, 9);
 
        model.addSeries(idealLine);
        model.addSeries(realLine);
         
        return model;
    }
 
}