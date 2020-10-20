package eu.larkc.csparql.core.engine;

import eu.larkc.csparql.common.RDFTriple;

import java.util.Set;

/**
 * @author 杨帆玉
 * @date 2020/2/6 9:50 下午
 */
public class ReasonResult {
    Set<RDFTriple> tripleSet;
    int time;

    public ReasonResult(Set<RDFTriple> tripleSet, int time) {
        this.tripleSet = tripleSet;
        this.time = time;
    }

    public ReasonResult() {
    }

    public Set<RDFTriple> getTripleSet() {
        return tripleSet;
    }

    public void setTripleSet(Set<RDFTriple> tripleSet) {
        this.tripleSet = tripleSet;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
