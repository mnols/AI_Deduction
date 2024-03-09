import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ResolutionLogique {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vous avez le droit de saisir: \nDe A à Z (atomes),\n^ (et),\n-A à -Z (les négations des atomes). \n");
        System.out.print("Entrez la FBF en séparant chaque clause par une virgule: ");
        String input = scanner.nextLine();
        
        String withNegation = ajouterNegation(input);
        
        if (estValide(withNegation)) {
            System.out.println("La FBF est valide");
        } else {
            System.out.println("La FBF est invalide");
        }
        scanner.close();
    }

    public static boolean estValide(String clauses) {
        List<List<String>> clausesList = new ArrayList<>();
        for (String clause : clauses.split(",")) {
            clausesList.add(Arrays.asList(clause.trim().split(" ")));
        }
        System.out.println("Clauses initiales: " + clausesList);

        if (clausesList.size() <= 2) {
            return estResolvable(clausesList.get(0), clausesList.get(1));
        }

        while (!estVide(clausesList) && existePaireReductible(clausesList)) {
            System.out.println("\nÉtape de résolution:");
            List<String> clause1;
            List<String> clause2;
            try {
                List<List<String>> pair = trouverPaireResolvable(clausesList);
                clause1 = pair.get(0);
                clause2 = pair.get(1);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("Paire résolvable: " + clause1 + " " + clause2);

            List<String> clauseResolvante = resoudre(clause1, clause2);
            System.out.println("Clause résolvante: " + clauseResolvante);

            clausesList.add(clauseResolvante);
            clausesList.remove(clause1);
            clausesList.remove(clause2);

            System.out.println("Clauses actuelles: " + clausesList);
        }

        boolean result = estVide(clausesList);
        System.out.println("\nRésultat final: " + result);
        return result;
    }

    public static boolean estVide(List<List<String>> clauses) {
        try {
            for (List<String> clause : clauses) {
                if (clause.isEmpty() || clause.stream().allMatch(literal -> "v".equals(literal))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification de la liste de clauses.");
            return false;
        }
    }

    public static boolean existePaireReductible(List<List<String>> clauses) {
        for (List<String> clause1 : clauses) {
            for (List<String> clause2 : clauses) {
                if (!clause1.equals(clause2) && estResolvable(clause1, clause2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<List<String>> trouverPaireResolvable(List<List<String>> clauses) throws Exception {
        for (List<String> clause1 : clauses) {
            for (List<String> clause2 : clauses) {
                if (!clause1.equals(clause2) && estResolvable(clause1, clause2)) {
                    List<List<String>> pair = new ArrayList<>();
                    pair.add(clause1);
                    pair.add(clause2);
                    return pair;
                }
            }
        }
        throw new Exception("Aucune paire de clauses résolvables trouvée");
    }

    public static boolean estResolvable(List<String> clause1, List<String> clause2) {
        for (String literal1 : clause1) {
            for (String literal2 : clause2) {
                if (literal1.equals(negation(literal2))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> resoudre(List<String> clause1, List<String> clause2) {
        String litteraireComp = null;
        for (String literal1 : clause1) {
            for (String literal2 : clause2) {
                if (literal1.equals(negation(literal2))) {
                    litteraireComp = literal1;
                    break;
                }
            }
        }

        if (litteraireComp != null) {
            List<String> clauseResolvante = new ArrayList<>();
            for (String literal : clause1) {
                if (!literal.equals(litteraireComp)) {
                    clauseResolvante.add(literal);
                }
            }
            for (String literal : clause2) {
                if (!literal.equals(negation(litteraireComp))) {
                    clauseResolvante.add(literal);
                }
            }
            return clauseResolvante;
        }
        return new ArrayList<>();
    }

    public static String negation(String literal) {
        if (literal.startsWith("-")) {
            return literal.substring(1);
        }
        return "-" + literal;
    }

    public static String ajouterNegation(String input) {
        StringBuilder withNegation = new StringBuilder();
        String[] clauses = input.split(",");
        for (String clause : clauses) {
            String[] literals = clause.trim().split(" ");
            for (String literal : literals) {
                if (!literal.equals("")) {
                   
                    literal = literal.replace("^", "v");
                    
                    if (literal.startsWith("-")) {
                        literal = literal.substring(1);
                    } else {
                        
                        if (!literal.equals("v")) {
                            literal = "-" + literal;
                        }
                    }
                    withNegation.append(literal).append(" ");
                }
            }
            withNegation.append(",");
        }
        return withNegation.toString();
    }
    
}
