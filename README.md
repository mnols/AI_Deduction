Le programme suivant est réalisé en Java et il vérifie la validité d'un ensemble des FBFs en utilisant la méthode de résolution. L'utilisateur saisit une FBF sans négation en spécifiant les atomes, l'opérateur ^ pour l'ET, et les clauses sont séparées par des virgules. Le programme ajoute automatiquement les négations nécessaires aux atomes pour former une FBF fermée, puis il applique la résolution sur les clauses.

Le processus de résolution identifie les paires de clauses résolvables, les résout en éliminant les littéraux opposés, et continue jusqu'à ce qu'il ne reste plus de clauses à résoudre ou qu'aucune nouvelle résolution ne soit possible. Finalement, le programme indique si la FBF est valide ou non.

On donne un exemple pour vérifier et tester le programme:

On saisit : A ^ -B, B, -A.
Il faut que le programme valide cet ensemble comme valide.

Le résultat d'exécution est : 

![image](https://github.com/mnols/AI_Deduction/assets/119527090/499d0548-45f7-4386-a695-bd005effbf09)

En conséquence, après avoir effectuée la négation et appliquée la résolution, l'ensemble est marqué comme valide.
