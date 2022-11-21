import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingList {
    public static void main(String[] args) {

        ArrayList<ShoppingItem> shoppingCart = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "=============================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "|=== CARRINHO DE COMPRAS ===|" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "=============================" + ConsoleColors.RESET);
        printAvailableActions();

        boolean loop = true;

        while(loop){

            int acao;
            do {
                System.out.print("Digite a ação desejada (digite 5 para ver as opções novamente): ");
                while (!sc.hasNextInt()) {
                    System.out.print("Por favor digite uma ação válida: ");
                    sc.next();
                }
                acao = sc.nextInt();
            } while (acao <  1 || acao > 5);

            switch (acao){
                case 1 -> shoppingCart = addShoppingListItem(shoppingCart, sc);
                case 2 -> shoppingCart = deleteShoppingListItem(shoppingCart, sc);
                case 3 -> showShoppingListItems(shoppingCart);
                case 4 -> loop = finalizeShoppingList(shoppingCart, sc);
                case 5 -> printAvailableActions();
            }
            acao = 0;

        }
    }

    private static void printAvailableActions(){
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Escolha uma das ações abaixo (digite 5 para ver as opções novamente):" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "1 - Adicionar um item ao carrinho" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "2 - Excluir um item do carrinho" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "3 - Vizualisar todos os itens" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "4 - Finalizar a compra" + ConsoleColors.RESET);
    }

    private static ArrayList<ShoppingItem> addShoppingListItem(ArrayList<ShoppingItem> shoppingCart, Scanner sc){
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\nAção Escolhida:" + ConsoleColors.BLUE_BOLD_BRIGHT +  " Adicionar um item ao carrinho" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"\n==========================================================" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Insira o nome do produto que deseja adicionar no carrinho: " + ConsoleColors.RESET);
        sc.nextLine();
        String itemName = sc.nextLine();

        System.out.println("\nAgora insira o preço do produto em reais (utilize ',' para separação decimal, e não coloque R$)");

        double itemPrice;
        do {
            System.out.print("Digite o preço do produto em reais: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Por favor digite um preço válido: ");
                sc.next();
            }
            itemPrice = sc.nextDouble();
            if(itemPrice <= 0) System.out.println("Por Favor digite um preço maior que zero");
        } while (itemPrice <=  0);

        System.out.println("\nPor fim, digite a quantidade deste produto que deseja comprar (máximo de 99)");
        int itemQty;
        do {
            System.out.print("Digite a quantidade desejada do produto: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Por favor digite uma quantidade válida: ");
                sc.next();
            }
            itemQty = sc.nextInt();
            if(itemQty <= 0) System.out.println("Por Favor digite uma quantidade maior que zero");
            if(itemQty > 99) System.out.println("Por Favor digite uma quantidade menor que 99");
        } while (itemQty <= 0 || itemQty > 99);
        ShoppingItem shoppingItem = new ShoppingItem(itemPrice, itemQty, itemName);
        shoppingCart.add(shoppingItem);
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Produto adicionado com Sucesso!" + ConsoleColors.RESET + "\n");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"==========================================================\n" + ConsoleColors.RESET);
        return shoppingCart;
    }

    private static ArrayList<ShoppingItem> deleteShoppingListItem(ArrayList<ShoppingItem> shoppingCart, Scanner sc) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\nAção Escolhida:" + ConsoleColors.BLUE_BOLD_BRIGHT +  " Excluir um item do carrinho" + ConsoleColors.RESET);
        if(shoppingCart.size() == 0){
            System.out.println("Você não possui nenhum produto no carrinho ainda\n");
            return new ArrayList<>();
        }
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n=====================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Produtos do carrinho: " + ConsoleColors.RESET);
        for (int i = 0; i < shoppingCart.size(); i++) {
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Produto " + (i+1) + ": " + shoppingCart.get(i).getItemName() + ConsoleColors.RESET);
        }
        System.out.println();
        int itemNumber;
        do {
            System.out.print("Digite número do produto que deseja excluir: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Por favor digite um produto válido: ");
                sc.next();
            }
            itemNumber = sc.nextInt();
            if(itemNumber < 1) System.out.println("Por Favor escolha um número de produto maior que zero");
        } while (itemNumber < 1);
        shoppingCart.remove(itemNumber-1);
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Produto " + (itemNumber-1) + " excluido com Sucesso!" + ConsoleColors.RESET + "\n");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n=====================" + ConsoleColors.RESET);
        return shoppingCart;
    }

    private static void showShoppingListItems(ArrayList<ShoppingItem> shoppingCart){
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\nAção Escolhida:" + ConsoleColors.BLUE_BOLD_BRIGHT +  " Vizualisar todos os itens" + ConsoleColors.RESET);
        if(shoppingCart.size() == 0){
            System.out.println("Você não possui nenhum produto no carrinho ainda\n");
            return;
        }
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n=====================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Produtos do carrinho: " + ConsoleColors.RESET);
        double finalPrice = 0d;
        for (int i = 0; i < shoppingCart.size(); i++){
            finalPrice += shoppingCart.get(i).getItemPrice()*shoppingCart.get(i).getItemQty();
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\nProduto " + (i+1) + ":"  + ConsoleColors.RESET);
            System.out.println("Nome: " + shoppingCart.get(i).getItemName());
            System.out.printf("Preço: R$%.2f\n", shoppingCart.get(i).getItemPrice());
            System.out.println("Quantidade: " + shoppingCart.get(i).getItemQty() + "\n");
        }
        System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT + "Preço Total: R$%.2f\n" + ConsoleColors.RESET, finalPrice);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "=====================\n" + ConsoleColors.RESET);
    }

    private static boolean finalizeShoppingList(ArrayList<ShoppingItem> shoppingCart,Scanner sc){
        if(shoppingCart.size() == 0){
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Você ainda não adicionou nenhum produto no carrinho, tem certeza que deseja finalizar a compra?" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "1 - SIM, QUERO FINALIZAR O PROCEDIMENTO" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "2 - NÃO, QUERO CONTINUAR COMPRANDO" + ConsoleColors.RESET);
            int acao;
            do {
                System.out.print("Digite a ação desejada: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Por favor digite uma ação válida: ");
                    sc.next();
                }
                acao = sc.nextInt();
            } while (acao <  1 || acao > 2);
            if(acao == 1) return false;
            if(acao == 2) return true;
        }
        double finalPrice = 0d;
        for (ShoppingItem shoppingItem : shoppingCart) {
            finalPrice += shoppingItem.getItemPrice()*shoppingItem.getItemQty();
        }
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\nAção Escolhida:" + ConsoleColors.BLUE_BOLD_BRIGHT +  " Finalizar a compra" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n=====================" + ConsoleColors.RESET);
        showShoppingListItems(shoppingCart);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + ConsoleColors.BLACK_BACKGROUND + "Obrigado pela preferência e volte sempre!" + ConsoleColors.RESET);
        return false;
    }
}