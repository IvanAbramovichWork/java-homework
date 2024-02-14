package org.example;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistic {
    private Double minNumber;
    private Double maxNumber;
    private int integersWritten;
    private int stringsWritten;
    private int floatsWritten;
    private BigDecimal sum;
    private BigDecimal average;
    private int minStrLen;
    private int maxStrLen;

    public Statistic() {
        this.integersWritten = 0;
        this.floatsWritten = 0;
        this.stringsWritten = 0;
        this.minNumber = Double.POSITIVE_INFINITY;
        this.maxNumber = Double.NEGATIVE_INFINITY;
        this.sum = BigDecimal.ZERO;
        this.average = BigDecimal.ZERO;
        this.minStrLen = Integer.MAX_VALUE;
        this.maxStrLen = Integer.MIN_VALUE;
    }

    public int getFloatsWritten() {
        return floatsWritten;
    }

    public int getIntegersWritten() {
        return integersWritten;
    }

    public int getStringWritten() {
        return stringsWritten;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public double getMaxNumber() {
        return maxNumber;
    }

    public double getMinNumber() {
        return minNumber;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public int getMaxStrLen() {
        return maxStrLen;
    }

    public int getMinStrLen() {
        return minStrLen;
    }

    public void setStringsWritten(int stringsWritten) {
        this.stringsWritten = stringsWritten;
    }

    public void setFloatsWritten(int floatsWritten) {
        this.floatsWritten = floatsWritten;
    }

    public void setIntegersWritten(int integersWritten) {
        this.integersWritten = integersWritten;
    }

    public void addString(String str) {
        this.stringsWritten++;
        if (str.length() > maxStrLen) {
            this.maxStrLen = str.length();
        }
        if (str.length() < minStrLen) {
            this.minStrLen = str.length();
        }
    }

    public void addInteger(Long in) {
        this.integersWritten++;
        if (in > maxNumber) {
            this.maxNumber = Double.valueOf(in);
        }
        if (in < minNumber) {
            this.minNumber = Double.valueOf(in);
        }
        this.sum = this.sum.add(BigDecimal.valueOf(in));
        this.average = this.sum.divide(BigDecimal.valueOf((getIntegersWritten() + getFloatsWritten())), RoundingMode.HALF_UP);
    }

    public void addFloat(double fl) {
        this.floatsWritten++;
        if (fl > maxNumber) {
            this.maxNumber = fl;
        }
        if (fl < minNumber) {
            this.minNumber = fl;
        }
        this.sum = this.sum.add(BigDecimal.valueOf(fl));
        this.average = this.sum.divide(BigDecimal.valueOf((getIntegersWritten() + getFloatsWritten())), RoundingMode.HALF_UP);
    }

    public Statistic addStatistic(Statistic statistic) {
        this.floatsWritten += statistic.getFloatsWritten();
        this.integersWritten += statistic.getIntegersWritten();
        this.stringsWritten += statistic.getStringWritten();
        this.average = this.sum.add(statistic.getSum()).divide(BigDecimal.valueOf(this.getIntegersWritten() + statistic.getIntegersWritten() + this.getFloatsWritten() + statistic.getFloatsWritten()), RoundingMode.HALF_UP);
        this.sum = this.sum.add(statistic.getSum());
        if (this.maxNumber > statistic.getMaxNumber()) this.maxNumber = statistic.getMaxNumber();
        if (this.minNumber < statistic.getMinNumber()) this.minNumber = statistic.getMinNumber();
        if (this.maxStrLen < statistic.getMaxStrLen()) this.maxStrLen = statistic.getMaxStrLen();
        if (this.minStrLen > statistic.getMinStrLen()) this.minStrLen = statistic.getMinStrLen();
        return this;
    }

    @Override
    public String toString() {
        return "integers written=" + integersWritten + "\n" + "strings written=" + stringsWritten + "\n" + "floatsWritten=" + floatsWritten + "\n"
                + "max number = " + this.maxNumber + "\n"
                + "min number = " + this.minNumber + "\n"
                + "average number = " + this.average + "\n"
                + "sum = " + this.sum + "\n"
                + "max string length = " + this.maxStrLen + "\n"
                + "min string length = " + this.minStrLen;
    }

    public void printFullStatistic() {
        System.out.println(this);
    }

    public void printShortStatistic() {
        System.out.println("integers written=" + integersWritten + "\n" + "strings written=" + stringsWritten + "\n" + "floatsWritten=" + floatsWritten);
    }
}
