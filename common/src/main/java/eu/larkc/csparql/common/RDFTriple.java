package eu.larkc.csparql.common;

import java.util.Objects;

/**
 * @author 杨帆玉
 * @date 2019/11/1 10:14 上午
 */
public class RDFTriple {
    public String subject;
    public String predicate;
    public String object;

    public RDFTriple() {
    }

    public RDFTriple(String subject, String predicate, String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "com.triple.common.eu.larkc.csparql.common.RDFTriple{" +
                "subject='" + subject + '\'' +
                ", predicate='" + predicate + '\'' +
                ", object='" + object + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RDFTriple rdfTriple = (RDFTriple) o;
        return subject.equals(rdfTriple.subject) &&
                predicate.equals(rdfTriple.predicate) &&
                object.equals(rdfTriple.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, predicate, object);
    }
}
