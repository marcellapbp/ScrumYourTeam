
package br.com.scrumyourteam.bean;
import br.com.scrumyourteam.controller.TaskController;
import br.com.scrumyourteam.domain.ChartInformation;
import br.com.scrumyourteam.domain.ChartPoints;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private ChartInformation chartInfo;
    private ArrayList<ChartPoints> listPoint;
        
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
            //it loads the points from team
            teamChart = initTeamChart();
            teamChart.setTitle("Burndown Sprint Atual");
            teamChart.setShowPointLabels(true);
            teamChart.setLegendPosition("e");
            
            //create x axis and set minimium point = -1
            Axis xAxis = teamChart.getAxis(AxisType.X);
            xAxis.setLabel("Duração Sprint");
            xAxis.setMax(chartInfo.getSetDateDiffTotal()+1); //day quantity
            xAxis.setMin(0);
            
            xAxis.setTickCount((int)(chartInfo.getSetDateDiffTotal()));
            xAxis.setTickFormat("%d");
           
            
            teamChart.getAxes().put(AxisType.X,xAxis);
            
            //create y axis and set min point = 0 and max = total estimate 
            Axis yAxis = teamChart.getAxis(AxisType.Y);
            yAxis.setLabel("Estimativa de Trabalho");
            yAxis.setMax(chartInfo.getTotalEstimate()+1);//work estimate 
            yAxis.setMin(0);
            
            yAxis.setTickCount(chartInfo.getTotalEstimate());
            yAxis.setTickFormat("%d");
            
            teamChart.getAxes().put(AxisType.Y,yAxis);
//            
//            //it loads the points from the individual
//            individualChart = initLinearModel();
//            individualChart.setTitle("Burndown Sprint Atual");
//            individualChart.setShowPointLabels(true);
//            individualChart.setLegendPosition("e");
//            individualChart.getAxes().put(AxisType.X,new Axis("Duração Sprint") {});
//            
//            yAxis = individualChart.getAxis(AxisType.Y);
//            yAxis.setLabel("Estimativa de Trabalho");
//            yAxis.setMin(0);
//            yAxis.setMax(100);
        } catch (SQLException ex) {
             throw new RuntimeException("Error to execute createLineModels in ChartView: " + ex);
        }
         
    }
     
    private LineChartModel initTeamChart() throws SQLException {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries idealLine = new LineChartSeries();
        idealLine.setLabel("Execução Ideal");
 
        TaskController control = new TaskController();
        
        
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
            
        chartInfo = control.getChartInformation((int) context.currentExternalContext().getSessionMap().get("idProject"));
        listPoint = (ArrayList<ChartPoints>) control.getChartPoints((int) context.currentExternalContext().getSessionMap().get("idProject"));
        
        idealLine.set(0, chartInfo.getTotalEstimate());
        idealLine.set(chartInfo.getSetDateDiffTotal(), 0 );
 
        LineChartSeries realLine = new LineChartSeries();
        realLine.setLabel("Execução Real");
 
        for(ChartPoints point : listPoint)
        {
            realLine.set(point.getNumberPoint(), point.getEstimatePoint());
        }
 
        model.addSeries(idealLine);
        model.addSeries(realLine);
         
        return model;
    }
 
}