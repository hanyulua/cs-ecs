package eu.larkc.csparql.core.cichlid;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.common.RDFTriple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 杨帆玉
 * @date 2020/2/3 1:04 下午
 */
public class RDFS {

    public static Set<RDFTriple> reason(List<RdfQuadruple> quads) {
        long start = System.currentTimeMillis();
        List<RDFTriple> triples = new ArrayList<>();
        for (RdfQuadruple t: quads) {
            triples.add(new RDFTriple(t.getSubject(), t.getPredicate(), t.getObject()));
        }
        long end = System.currentTimeMillis();
        System.out.println("set化时间: " + (end - start));
//			printList(triples, "triples");
        List<RDFTriple> r7_out = ForwardReasoner.r7_out(triples);
//			printList(r7_out, "r7_out");
        r7_out.addAll(triples);
//			printList(r7_out, "r7_out");
        List<RDFTriple> r23_out = ForwardReasoner.r23_out(r7_out);
//			printList(r23_out, "r23_out");
        List<RDFTriple> tp = new ArrayList<>();
        for (RDFTriple t: triples) {
            if (t.predicate.equals(Rules.RDFS_TYPE)) {
                tp.add(t);
            }
        }
        r23_out.addAll(tp);
        List<RDFTriple> r9_out = ForwardReasoner.r9_out(r23_out);

        r23_out.addAll(r9_out);
//			r23_out.addAll(r9_out);
        r23_out.addAll(r7_out);
        r23_out.addAll(triples);
//			System.out.println("推理Triple: " + tripleSet.size());
        end = System.currentTimeMillis();
        System.out.println("推理结果: " + r23_out.size());
        System.out.println("推理时间: " + (end - start));
        return new HashSet<>(r23_out);
    }
}
