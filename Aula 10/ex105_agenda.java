/* Agenda - Rafael Ferreira
 * Dúvidas: rafael.ferreira@ua.pt
 * wwww.rafaelferreira.pt/recursos
 * Alguma falha na resolucao por favor reporte para rafael.ferreira@ua.pt !
 */
import java.util.*;
import static java.lang.System.*;

public class ex105_agenda {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        contacto[] agenda = new contacto[100];
        for(int i=0; i<agenda.length; i++)
           agenda[i] = new contacto();
        
        int nmenu;
        int n=-1;
        do{
            nmenu=menu();
            switch(nmenu){
                case 1: n=InserirContacto(agenda, n);
                    break;
                case 2: SearchContacto(agenda, n);
                    break;
                case 3: n = DelContacto(agenda, n);
                    break;
                case 4: VisualizarContactos(agenda, n);
                    break;
                case 5: OddNome(agenda, n);
                    break;
                case 6: ValidarEmails(agenda, n);
                    break;
                case 7: n = DelAgenda(agenda);
                    break;
            }
        }while(nmenu!=8);
       
    }
    public static int menu(){
        
        out.println("Gestão de uma agenda:");
        out.println("1 - Inserir um contacto");
        out.println("2 - Pesquisar contacto por nome");
        out.println("3 - Eliminar um contacto");
        out.println("4 - Visualizar todos os contactos");
        out.println("5 - Visualizar contactos ordenados por nome");
        out.println("6 - Validar endereços de email");
        out.println("7 - Apagar a agenda");
        out.println("8 - Terminar o programa");
        out.print("Opção -> "); return sc.nextInt();

    }
    public static int InserirContacto(contacto[] a, int n){
        //"Nao e necessario fazer validacoes na introducao dos dados."
        char d;
        sc.nextLine(); 
        do{
           n++; 
           out.println("\nInserir novo contacto:\n");
           out.print("Nome: "); a[n].nome=sc.nextLine();
           out.print("Morada: "); a[n].morada=sc.nextLine();
           out.print("Telefone: "); a[n].telefone=sc.nextInt();
           sc.nextLine();
           out.print("Email: "); a[n].email=sc.nextLine();
           out.print("\nInserir mais um contacto? (y/n): "); d=Character.toLowerCase(sc.nextLine().charAt(0)); 
        }while(d=='y');
        out.println();
        return n; //numero exacto em q esta
    }
    public static void SearchContacto(contacto[] a, int n){
        sc.nextLine();
        out.println("\nDeseja visualizar a informação de que contacto?");
        out.print("Digite um nome: "); 
        String nm = new String();
        nm=sc.nextLine(); //nome que quer procurar
        int max=-1;
        
        for(int i=0; i<=n; i++)
            if(a[i].nome.indexOf(nm)>max)
                max=i;
            
        if(max!=-1)
            ImprimirContacto(a[max]);
        else
            out.println("\nNão foi encontrado nenhum contacto!\n");
    }
    public static void ImprimirContacto(contacto a){
        out.println("\nNome: "+a.nome);
        out.println("Morada: "+a.morada);
        out.println("Telefone: "+a.telefone);
        out.println("Email: "+a.email+"\n");
    }
    public static int DelContacto(contacto[] a, int n){
        out.println("\nEliminar contacto");
        int numero;
        out.print("\nNúmero de telefone: "); numero = sc.nextInt();
        int i=0, b;
        for(; i<=n; i++)
            if(a[i].telefone==numero){
                out.println("\nRemovido o contacto : "+a[i].nome+"\n");
                for(b=i; b<n; b++){
                    a[b].nome=a[b+1].nome;
                    a[b].morada=a[b+1].morada;
                    a[b].email=a[b+1].email;
                    a[b].telefone=a[b+1].telefone;
		}
		return --n;
            }
        
        out.println("Não foi encontrado nenhum livro!\n");
        return n;
    }
    public static void VisualizarContactos(contacto[] a, int n){
        if(n!=-1)
            for(int i=0; i<=n; i++)
                ImprimirContacto(a[i]);
        else
            out.println("\nNão existem contactos na base de dados!\n");
    }
    public static void OddNome(contacto[] a, int n){
        int[] ordem = new int[n+1];
        String nome[] = new String[n+1];
        
        for(int i=0; i<=n; i++){
            ordem[i]=i;
            nome[i]=a[i].nome;
        }
            
        for(int i=0; i<n; i++)
            for(int c=i+1; c<=n; c++)
                if(a[c].nome.compareTo(a[i].nome)<0){
                    //ordenando as strings
                    String tmp = nome[c];
                    nome[c]=nome[i];
                    nome[i]=tmp;
                    //ordem correspondente a array 
                    int odd = ordem[c];
                    ordem[c]=ordem[i];
                    ordem[i]=odd;  
                }    
        
        for(int i=0; i<=n; i++)
            ImprimirContacto(a[ordem[i]]);
    }
    public static void ValidarEmails(contacto[] a, int n){
        sc.nextLine();
        boolean valido=true;
        for(int i=0; i<=n; i++)
            if(!ValEmail(a[i].email)){
                out.println("\nEmail invalido!");
                ImprimirContacto(a[i]);
                out.print("Novo email: "); a[i].email=sc.nextLine();
                out.println();
                valido=false;
            }
        
        if(valido)
           out.println("\nOs endereços estão todo válidos!\n");
    }
    public static boolean ValEmail(String email){//true valido, false invalido
        int i=0;
        
        do{
            if(email.charAt(i)!='@' && email.charAt(i)!='.' && email.charAt(i)!='_' && !Character.isLetterOrDigit(email.charAt(i)))
               return false;
        }while(++i!=email.length());
        
        int arroba=0;
        
        for(i=0; i<email.length(); i++)
            if(email.charAt(i)=='@')
                arroba++;
        
        if(arroba!=1)
            return false;
        else
            return true;
    }
    public static int DelAgenda(contacto[] a){
        for(int i=0; i<a.length; i++)
            a[i] = new contacto();
        out.println("\nApagada a base de dados!\n");
        return -1;
    }
}
class contacto{
    String nome, morada, email;
    int telefone;
}