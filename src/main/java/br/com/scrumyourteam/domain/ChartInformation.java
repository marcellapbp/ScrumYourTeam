package br.com.scrumyourteam.domain;

import java.time.LocalDate;

public class ChartInformation 
{
    private int totalEstimate;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private int setDateDiffTotal;

    public int getTotalEstimate() {
        return totalEstimate;
    }

    public void setTotalEstimate(int totalEstimate) {
        this.totalEstimate = totalEstimate;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public int getSetDateDiffTotal() {
        return setDateDiffTotal;
    }

    public void setSetDateDiffTotal(int setDateDiffTotal) {
        this.setDateDiffTotal = setDateDiffTotal;
    }
}
