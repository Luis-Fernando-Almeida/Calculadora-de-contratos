package entities;

import entities.enums.WorkerLevel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Worker {
    private String name;
    private WorkerLevel level;
    private Double baseSalary;

    private Department department;
    private List<HourContract> contracts = new ArrayList<>(); //
    /* quando você tiver uma composição que tem muitos dados,
    como no caso de uma lista, não incluir no construtor, somente iniciar a lista*/

    public Worker(){
    }

    public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }

    /*public void setContracts(List<HourContract> contracts) {
        this.contracts = contracts;
    }
     não é recomendável utilizar esse método pois eles vai substituir toda a lista de contratos, o que não é a função desejada.
     sendo melhor adicionar ou remover um contrato por vez*/

    public void addContract(HourContract contract){ //esse método vai adicionar o contato passado como argumento na list de contratos
        contracts.add(contract);
    }

    public void removeContract(HourContract contract){ //esse método vai remover o contato passado como argumento na list de contratos
        contracts.remove(contract);
    }

    public double income(int year, int month){ //declaração do método passando 2 parâmetros
        double sum = baseSalary; // variavel que recebe o salário base para calcular o salário mais o valor dos contratos
        Calendar cal = Calendar.getInstance(); // uma instância do objeto calendar para a manipulação das datas
        for (HourContract c : contracts){ //for each para iterar sobre todos os contratos(HourContract) na lista (contracts)
            cal.setTime(c.getDate()); //configura a data do contrato c no objeto Calendar permitindo a extração do mes e do ano
            int c_year = cal.get(Calendar.YEAR);//obtém o ano do contrato
            int c_month = 1 + cal.get(Calendar.MONTH); //obtém o mês do contrato
            if (year == c_year && month == c_month){  // verificar se o mês e o ano coincidem com os valores passados nos parâmetros
                sum += c.totalValue(); // se a condição acima for verdadeira soma o salário base mais os contratos do mês em questão
            }
        }
        return sum;
    }
}
