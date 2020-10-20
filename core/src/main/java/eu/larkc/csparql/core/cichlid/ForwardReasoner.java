package eu.larkc.csparql.core.cichlid;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.common.RDFTriple;

import java.util.*;

/**
 * @author 杨帆玉
 * @date 2019/11/26 2:56 下午
 */
public class ForwardReasoner {
    private List<RDFTriple> triples;

    // 初始化模式三元组

    public static HashMap<String, List<String>> sp = toMapList(transitiveClosure(SchemeTriple.getSubPropSet()));
    public static HashMap<String, List<String>> cl = toMapList(transitiveClosure(SchemeTriple.getSubClassSet()));
    public static HashMap<String, String> dm = toMap(SchemeTriple.getDomainSet());
    public static HashMap<String, String> rg = toMap(SchemeTriple.getRangeSet());

    public List<RDFTriple> getTriples() {
        return triples;
    }

    public void setTriples(List<RDFTriple> triples) {
        this.triples = triples;
    }

    public ForwardReasoner(List<RdfQuadruple> triples) {
        for (RdfQuadruple t: triples) {
            this.triples.add(new RDFTriple(t.getSubject(), t.getPredicate(), t.getObject()));
        }
    }

    private static HashSet<RDFTriple> transitiveClosure(HashSet<RDFTriple> set) {
        HashSet<RDFTriple> t = new HashSet<>(set);
        addTransitive(set);
        if (t.size() == set.size()) {
            return t;
        } else {
            return transitiveClosure(set);
        }
    }

    private static void addTransitive(HashSet<RDFTriple> set) {
        HashSet<RDFTriple> res = new HashSet<>();
        for (RDFTriple rdf: set) {
            for (RDFTriple rdf1: set) {
                if (rdf.object.equals(rdf1.subject)) {
                    res.add(new RDFTriple(rdf.subject, rdf.predicate, rdf1.object));
                }
            }
        }
        set.addAll(res);
    }

    private static HashMap<String, String> toMap(HashSet<RDFTriple> set) {
        HashMap<String, String> map = new HashMap<>();
        for (RDFTriple rdf: set) {
            map.put(rdf.subject, rdf.object);
        }
        return map;
    }

    private static HashMap<String, List<String>> toMapList(HashSet<RDFTriple> set) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (RDFTriple rdf: set) {
            if (map.containsKey(rdf.subject)) {
                map.get(rdf.subject).add(rdf.object);
            } else {
                List<String> list = new ArrayList<>();
                list.add(rdf.object);
                map.put(rdf.subject, list);
            }
        }
        return map;
    }

    //rule 7: s p o & p rdfs:subPropertyOf q => s q o
    public static List<RDFTriple> r7_out(List<RDFTriple> triples) {
        List<RDFTriple> out = new ArrayList<>();
        for (RDFTriple t: triples) {
            if (sp.containsKey(t.getPredicate())) {
                for (String s: sp.get(t.predicate)) {
                    out.add(new RDFTriple(t.subject, s, t.object));
                }
            }
        }
        return out;
    }

    //rule 2:p rdfs:domain x & s p o => s rdf:type x
    //rule 3:p rdfs:range x & s p o => o rdf:type x
    public static List<RDFTriple> r23_out(List<RDFTriple> triples) {
        List<RDFTriple> out2 = new ArrayList<>();
        List<RDFTriple> out3 = new ArrayList<>();
        for (RDFTriple t: triples) {
            if (dm.containsKey(t.getPredicate())) {
                out2.add(new RDFTriple(t.subject, Rules.RDFS_TYPE, dm.get(t.predicate)));
            }
            if (rg.containsKey(t.getPredicate())) {
                out3.add(new RDFTriple(t.object, Rules.RDFS_TYPE, rg.get(t.predicate)));
            }
        }
        out2.addAll(out3);
        return out2;
    }

    //rule 9:s rdf:type x & x rdfs:subClassOf y => s rdf:type y
    public static List<RDFTriple> r9_out(List<RDFTriple> triples) {
        List<RDFTriple> out = new ArrayList<>();
        for (RDFTriple t: triples) {
            if (cl.containsKey(t.getObject())) {
                for (String o: cl.get(t.object)) {
                    out.add(new RDFTriple(t.subject, Rules.RDFS_TYPE, o));
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        for (String s: ForwardReasoner.cl.keySet()) {
            System.out.println(s + "   " + ForwardReasoner.cl.get(s));
        }
        System.out.println("**********");
    }
}
