package eu.larkc.csparql.core.cichlid;

import eu.larkc.csparql.common.RDFTriple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author 杨帆玉
 * @date 2019/11/5 10:41 上午
 */
public class SchemeTriple {
    public static final RDFTriple posts = new RDFTriple(
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#posts",
            "http://www.w3.org/2000/01/rdf-schema#subPropertyOf",
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#observes");
    public static final RDFTriple observes1 = new RDFTriple(
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#observes",
            "http://www.w3.org/2000/01/rdf-schema#domain",
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#Sensor");
    public static final RDFTriple observes2 = new RDFTriple(
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#observes",
            "http://www.w3.org/2000/01/rdf-schema#range",
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#Observation");
    public static final RDFTriple Person = new RDFTriple(
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#Person",
            "http://www.w3.org/2000/01/rdf-schema#subClassOf",
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#Sensor");
    public static final RDFTriple Post = new RDFTriple(
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#Post",
            "http://www.w3.org/2000/01/rdf-schema#subClassOf",
            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#Observation");
    public static final RDFTriple scheme1 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Article", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme2 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Article", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme3 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#listedCourse", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme4 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#listedCourse", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Schedule");
    public static final RDFTriple scheme5 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#listedCourse", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course");
    public static final RDFTriple scheme6 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#age", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#DatatypeProperty");
    public static final RDFTriple scheme7 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#age", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme8 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme9 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#softwareDocumentation", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme10 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#softwareDocumentation", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Software");
    public static final RDFTriple scheme11 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#softwareDocumentation", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme12 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationDate", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme13 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationDate", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme14 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#title", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#DatatypeProperty");
    public static final RDFTriple scheme15 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#title", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme16 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#PostDoc", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme17 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#PostDoc", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Faculty");
    public static final RDFTriple scheme18 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Director", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme19 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme20 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme21 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme22 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#subOrganizationOf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#TransitiveProperty");
    public static final RDFTriple scheme23 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#subOrganizationOf", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme24 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#subOrganizationOf", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme25 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ConferencePaper", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme26 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ConferencePaper", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Article");
    public static final RDFTriple scheme27 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ClericalStaff", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme28 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ClericalStaff", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AdministrativeStaff");
    public static final RDFTriple scheme29 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#orgPublication", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme30 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#orgPublication", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme31 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#orgPublication", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme32 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#VisitingProfessor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme33 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#VisitingProfessor", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme34 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Employee", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme35 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme36 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Faculty");
    public static final RDFTriple scheme37 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course");
    public static final RDFTriple scheme38 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#JournalArticle", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme39 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#JournalArticle", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Article");
    public static final RDFTriple scheme40 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#DatatypeProperty");
    public static final RDFTriple scheme41 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme42 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme43 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme44 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#DatatypeProperty");
    public static final RDFTriple scheme45 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchGroup", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme46 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchGroup", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme47 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#College", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme48 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#College", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme49 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme50 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme51 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme52 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme53 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University");
    public static final RDFTriple scheme54 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom", "http://www.w3.org/2002/07/owl#inverseOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#hasAlumnus");
    public static final RDFTriple scheme55 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme56 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Work");
    public static final RDFTriple scheme57 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AdministrativeStaff", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme58 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AdministrativeStaff", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Employee");
    public static final RDFTriple scheme59 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme60 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Student");
    public static final RDFTriple scheme61 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AssociateProfessor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme62 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AssociateProfessor", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme63 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Student", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme64 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#TeachingAssistant", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme65 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teachingAssistantOf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme66 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teachingAssistantOf", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#TeachingAssistant");
    public static final RDFTriple scheme67 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teachingAssistantOf", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course");
    public static final RDFTriple scheme68 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchAssistant", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme69 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchAssistant", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme70 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#DatatypeProperty");
    public static final RDFTriple scheme71 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme72 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Lecturer", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme73 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Lecturer", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Faculty");
    public static final RDFTriple scheme74 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#affiliatedOrganizationOf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme75 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#affiliatedOrganizationOf", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme76 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#affiliatedOrganizationOf", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme77 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Department", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme78 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Department", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme79 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Ontology");
    public static final RDFTriple scheme80 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Program", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme81 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Program", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme82 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Schedule", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme83 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme84 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme85 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University");
    public static final RDFTriple scheme86 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#subPropertyOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom");
    public static final RDFTriple scheme87 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Chair", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme88 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Chair", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme89 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Manual", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme90 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Manual", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme91 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Institute", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme92 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Institute", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme93 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme94 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Faculty");
    public static final RDFTriple scheme95 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationResearch", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme96 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationResearch", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme97 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationResearch", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Research");
    public static final RDFTriple scheme98 = new RDFTriple("http://www.w3.org/2000/01/rdf-schema#takesCourse", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme99 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Specification", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme100 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Specification", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme101 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#affiliateOf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme102 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#affiliateOf", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme103 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#affiliateOf", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme104 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#FullProfessor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme105 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#FullProfessor", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme106 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#TechnicalReport", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme107 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#TechnicalReport", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Article");
    public static final RDFTriple scheme108 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#headOf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme109 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#headOf", "http://www.w3.org/2000/01/rdf-schema#subPropertyOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor");
    public static final RDFTriple scheme110 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Work", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme111 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#softwareVersion", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme112 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#softwareVersion", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Software");
    public static final RDFTriple scheme113 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme114 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Research", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme115 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Research", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Work");
    public static final RDFTriple scheme116 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme117 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme118 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University");
    public static final RDFTriple scheme119 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#subPropertyOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom");
    public static final RDFTriple scheme120 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme121 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course");
    public static final RDFTriple scheme122 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AssistantProfessor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme123 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AssistantProfessor", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme124 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme125 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme126 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme127 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University");
    public static final RDFTriple scheme128 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.w3.org/2000/01/rdf-schema#subPropertyOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom");
    public static final RDFTriple scheme129 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Dean", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme130 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Dean", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme131 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme132 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme133 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme134 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchProject", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme135 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchProject", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchGroup");
    public static final RDFTriple scheme136 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchProject", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Research");
    public static final RDFTriple scheme137 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#SystemsStaff", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme138 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#SystemsStaff", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AdministrativeStaff");
    public static final RDFTriple scheme139 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Faculty", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme140 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Faculty", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Employee");
    public static final RDFTriple scheme141 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#hasAlumnus", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme142 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#hasAlumnus", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University");
    public static final RDFTriple scheme143 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#hasAlumnus", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme144 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#hasAlumnus", "http://www.w3.org/2002/07/owl#inverseOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#degreeFrom");
    public static final RDFTriple scheme145 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Software", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme146 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Software", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme147 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchInterest", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#DatatypeProperty");
    public static final RDFTriple scheme148 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Book", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme149 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Book", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme150 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#member", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme151 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#member", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Organization");
    public static final RDFTriple scheme152 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#member", "http://www.w3.org/2000/01/rdf-schema#range", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Person");
    public static final RDFTriple scheme153 = new RDFTriple("http://www.w3.org/2000/01/rdf-schema#memberOf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme154 = new RDFTriple("http://www.w3.org/2000/01/rdf-schema#memberOf", "http://www.w3.org/2002/07/owl#inverseOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#member");
    public static final RDFTriple scheme155 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UnofficialPublication", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#Class");
    public static final RDFTriple scheme156 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UnofficialPublication", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication");
    public static final RDFTriple scheme157 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#officeNumber", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#DatatypeProperty");
    public static final RDFTriple scheme158 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#tenured", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme159 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#tenured", "http://www.w3.org/2000/01/rdf-schema#domain", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Professor");
    public static final RDFTriple scheme160 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2002/07/owl#ObjectProperty");
    public static final RDFTriple scheme161 = new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.w3.org/2000/01/rdf-schema#subPropertyOf", "http://www.w3.org/2000/01/rdf-schema#memberOf");
    public static final RDFTriple scheme162 = new RDFTriple("http://www.w3.org/2000/01/rdf-schema#schoolMate", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2000/01/rdf-schema#transitiveProperty");
    public static final RDFTriple scheme163 = new RDFTriple("http://www.w3.org/2000/01/rdf-schema#classMate", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.w3.org/2000/01/rdf-schema#transitiveProperty");

    //
//    public static final com.triple.common.eu.larkc.csparql.common.RDFTriple A1 = new com.triple.common.eu.larkc.csparql.common.RDFTriple(
//            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#a",
//            "http://www.w3.org/2000/01/rdf-schema#subClassOf",
//            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#b");
//    public static final com.triple.common.eu.larkc.csparql.common.RDFTriple A2 = new com.triple.common.eu.larkc.csparql.common.RDFTriple(
//            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#b",
//            "http://www.w3.org/2000/01/rdf-schema#subClassOf",
//            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#c");
//    public static final com.triple.common.eu.larkc.csparql.common.RDFTriple A3 = new com.triple.common.eu.larkc.csparql.common.RDFTriple(
//            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#c",
//            "http://www.w3.org/2000/01/rdf-schema#subClassOf",
//            "http://www.streamreasoning.org/ontologies/sr4ld2014-onto#d");
    // 获取所有的 scheme
    public static List<RDFTriple> getSchemeList161 = Arrays.asList(scheme1, scheme2, scheme3, scheme4, scheme5, scheme6, scheme7, scheme8, scheme9, scheme10, scheme11, scheme12, scheme13, scheme14, scheme15, scheme16, scheme17, scheme18, scheme19, scheme20, scheme21, scheme22, scheme23, scheme24, scheme25, scheme26, scheme27, scheme28, scheme29, scheme30, scheme31, scheme32, scheme33, scheme34, scheme35, scheme36, scheme37, scheme38, scheme39, scheme40, scheme41, scheme42, scheme43, scheme44, scheme45, scheme46, scheme47, scheme48, scheme49, scheme50, scheme51, scheme52, scheme53, scheme54, scheme55, scheme56, scheme57, scheme58, scheme59, scheme60, scheme61, scheme62, scheme63, scheme64, scheme65, scheme66, scheme67, scheme68, scheme69, scheme70, scheme71, scheme72, scheme73, scheme74, scheme75, scheme76, scheme77, scheme78, scheme79, scheme80, scheme81, scheme82, scheme83, scheme84, scheme85, scheme86, scheme87, scheme88, scheme89, scheme90, scheme91, scheme92, scheme93, scheme94, scheme95, scheme96, scheme97, scheme98, scheme99, scheme100, scheme101, scheme102, scheme103, scheme104, scheme105, scheme106, scheme107, scheme108, scheme109, scheme110, scheme111, scheme112, scheme113, scheme114, scheme115, scheme116, scheme117, scheme118, scheme119, scheme120, scheme121, scheme122, scheme123, scheme124, scheme125, scheme126, scheme127, scheme128, scheme129, scheme130, scheme131, scheme132, scheme133, scheme134, scheme135, scheme136, scheme137, scheme138, scheme139, scheme140, scheme141, scheme142, scheme143, scheme144, scheme145, scheme146, scheme147, scheme148, scheme149, scheme150, scheme151, scheme152, scheme153, scheme154, scheme155, scheme156, scheme157, scheme158, scheme159, scheme160, scheme161);
    public static List<RDFTriple> getSchemeList163 = Arrays.asList(scheme1, scheme2, scheme3, scheme4, scheme5, scheme6, scheme7, scheme8, scheme9, scheme10, scheme11, scheme12, scheme13, scheme14, scheme15, scheme16, scheme17, scheme18, scheme19, scheme20, scheme21, scheme22, scheme23, scheme24, scheme25, scheme26, scheme27, scheme28, scheme29, scheme30, scheme31, scheme32, scheme33, scheme34, scheme35, scheme36, scheme37, scheme38, scheme39, scheme40, scheme41, scheme42, scheme43, scheme44, scheme45, scheme46, scheme47, scheme48, scheme49, scheme50, scheme51, scheme52, scheme53, scheme54, scheme55, scheme56, scheme57, scheme58, scheme59, scheme60, scheme61, scheme62, scheme63, scheme64, scheme65, scheme66, scheme67, scheme68, scheme69, scheme70, scheme71, scheme72, scheme73, scheme74, scheme75, scheme76, scheme77, scheme78, scheme79, scheme80, scheme81, scheme82, scheme83, scheme84, scheme85, scheme86, scheme87, scheme88, scheme89, scheme90, scheme91, scheme92, scheme93, scheme94, scheme95, scheme96, scheme97, scheme98, scheme99, scheme100, scheme101, scheme102, scheme103, scheme104, scheme105, scheme106, scheme107, scheme108, scheme109, scheme110, scheme111, scheme112, scheme113, scheme114, scheme115, scheme116, scheme117, scheme118, scheme119, scheme120, scheme121, scheme122, scheme123, scheme124, scheme125, scheme126, scheme127, scheme128, scheme129, scheme130, scheme131, scheme132, scheme133, scheme134, scheme135, scheme136, scheme137, scheme138, scheme139, scheme140, scheme141, scheme142, scheme143, scheme144, scheme145, scheme146, scheme147, scheme148, scheme149, scheme150, scheme151, scheme152, scheme153, scheme154, scheme155, scheme156, scheme157, scheme158, scheme159, scheme160, scheme161, scheme162, scheme163);
    // 获取二级迭代的 scheme
    public static HashSet<RDFTriple> getSubPropSet() {
        HashSet<RDFTriple> hashSet = new HashSet<>();
        for (RDFTriple rdf: getSchemeList161) {
            if (rdf.predicate.equals(Rules.RDFS_SUBPROPERTY_OF))
                hashSet.add(rdf);
        }
        return hashSet;
    }
    public static HashSet<RDFTriple> getSubClassSet() {
        HashSet<RDFTriple> hashSet = new HashSet<>();
        for (RDFTriple rdf: getSchemeList161) {
            if (rdf.predicate.equals(Rules.RDFS_SUBCLASS_OF))
                hashSet.add(rdf);
        }
        return hashSet;
    }
    public static HashSet<RDFTriple> getRangeSet() {
        HashSet<RDFTriple> hashSet = new HashSet<>();
        for (RDFTriple rdf: getSchemeList161) {
            if (rdf.predicate.equals(Rules.RDFS_RANGE))
                hashSet.add(rdf);
        }
        return hashSet;
    }
    public static HashSet<RDFTriple> getDomainSet() {
        HashSet<RDFTriple> hashSet = new HashSet<>();
        for (RDFTriple rdf: getSchemeList161) {
            if (rdf.predicate.equals(Rules.RDFS_DOMAIN))
                hashSet.add(rdf);
        }
        return hashSet;
    }
}
