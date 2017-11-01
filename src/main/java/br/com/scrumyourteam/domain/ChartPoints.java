
package br.com.scrumyourteam.domain;

import java.time.LocalDate;

public class ChartPoints 
{
    private LocalDate DatePoint;
    private int NumberPoint;
    private int EstimatePoint;

    public LocalDate getDatePoint() {
        return DatePoint;
    }

    public void setDatePoint(LocalDate DatePoint) {
        this.DatePoint = DatePoint;
    }

    public int getNumberPoint() {
        return NumberPoint;
    }

    public void setNumberPoint(int NumberPoint) {
        this.NumberPoint = NumberPoint;
    }

    public int getEstimatePoint() {
        return EstimatePoint;
    }

    public void setEstimatePoint(int EstimatePoint) {
        this.EstimatePoint = EstimatePoint;
    }
}
