package eu.larkc.csparql.cep.esper;

import eu.larkc.csparql.cep.api.RdfQuadruple;

import java.util.List;

/**
 * @author 杨帆玉
 * @date 2020/2/5 3:08 下午
 */
public class WinList {
    int time;
    List<RdfQuadruple> quadrupleList;

    public WinList() {
    }

    public WinList(int time, List<RdfQuadruple> quadrupleList) {
        this.time = time;
        this.quadrupleList = quadrupleList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<RdfQuadruple> getQuadrupleList() {
        return quadrupleList;
    }

    public void setQuadrupleList(List<RdfQuadruple> quadrupleList) {
        this.quadrupleList = quadrupleList;
    }
}
