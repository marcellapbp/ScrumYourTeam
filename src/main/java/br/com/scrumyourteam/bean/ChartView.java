
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
    private ChartInformation chartInfoInd;
    private ArrayList<ChartPoints> listPointInd;
        
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

            
            //*****************************************************************
            //it loads the points from individual
            individualChart = initIndividualChart();
            individualChart.setTitle("Burndown Sprint Atual");
            individualChart.setShowPointLabels(true);
            individualChart.setLegendPosition("e");
            
            //create x axis and set minimium point = -1
            Axis xAxisInd = individualChart.getAxis(AxisType.X);
            xAxisInd.setLabel("Duração Sprint");
            xAxisInd.setMax(chartInfoInd.getSetDateDiffTotal()+1); //day quantity
            xAxisInd.setMin(0);
            
            xAxisInd.setTickCount((int)(chartInfoInd.getSetDateDiffTotal()));
            xAxisInd.setTickFormat("%d");
           
            
            individualChart.getAxes().put(AxisType.X,xAxisInd);
            
            //create y axis and set min point = 0 and max = total estimate 
            Axis yAxisInd = individualChart.getAxis(AxisType.Y);
            yAxisInd.setLabel("Estimativa de Trabalho");
            yAxisInd.setMax(chartInfoInd.getTotalEstimate()+1);//work estimate 
            yAxisInd.setMin(0);
            
            yAxisInd.setTickCount(chartInfoInd.getTotalEstimate());
            yAxisInd.setTickFormat("%d");
            
            individualChart.getAxes().put(AxisType.Y,yAxisInd);

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
    
    private LineChartModel initIndividualChart() throws SQLException {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries idealLine = new LineChartSeries();
        idealLine.setLabel("Execução Ideal");
 
        TaskController control = new TaskController();
        
        
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        int idProject = ((int) context.currentExternalContext().getSessionMap().get("idProject"));
        int idUser = ((int) context.currentExternalContext().getSessionMap().get("idUser"));
        chartInfoInd = control.getChartInformationIndividual(idProject, idUser);
        listPointInd = (ArrayList<ChartPoints>) control.getChartPointsIndividual(idProject, idUser);
        
        idealLine.set(0, chartInfoInd.getTotalEstimate());
        idealLine.set(chartInfoInd.getSetDateDiffTotal(), 0 );
 
        LineChartSeries realLine = new LineChartSeries();
        realLine.setLabel("Execução Real");
 
        for(ChartPoints point : listPointInd)
        {
            realLine.set(point.getNumberPoint(), point.getEstimatePoint());
        }
 
        model.addSeries(idealLine);
        model.addSeries(realLine);
         
        return model;
    }
}