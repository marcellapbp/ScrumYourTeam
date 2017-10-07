
package br.com.scrumyourteam.bean;
import br.com.scrumyourteam.controller.TaskController;
import br.com.scrumyourteam.domain.ChartInformation;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private LineChartModel teamChart;
    private LineChartModel individualChart;
    private SessionContext context;
    private HttpServletRequest request;
        
    public ChartView() 
    {
         this.context = new SessionContext();
    }
    
    
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
        
        try {
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
        } catch (SQLException ex) {
             throw new RuntimeException("Error to execute createLineModels in ChartView: " + ex);
        }
         
    }
     
    private LineChartModel initLinearModel() throws SQLException {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries idealLine = new LineChartSeries();
        idealLine.setLabel("Execução Ideal");
 
        TaskController control = new TaskController();
        ChartInformation chartInfo = new ChartInformation();
        
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
            
        chartInfo = control.getChartInformation((int) context.currentExternalContext().getSessionMap().get("idProject"));
        
        idealLine.set(0, chartInfo.getTotalEstimate());
        idealLine.set(10, 0 );
 
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