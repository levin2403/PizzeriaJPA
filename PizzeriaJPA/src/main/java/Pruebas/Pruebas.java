/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Pruebas;

import DAO.EmpleadoDAO;
import DAO.IngredienteDAO;
import DAO.ProductoDAO;
import DAO.TipoIngredienteDAO;
import DAO.VentaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import persistencia.Empleado;
import persistencia.Ingrediente;
import persistencia.Producto;
import persistencia.TipoIngrediente;
import persistencia.Venta;


public class Pruebas {

 public static void main(String[] args) throws ParseException {
        
        ProductoDAO productoDAO = new ProductoDAO();
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        TipoIngredienteDAO tipoDAO = new TipoIngredienteDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        VentaDAO ventaDAO = new VentaDAO();
        
        try {

            TipoIngrediente tipo1 = new TipoIngrediente("Base");
            TipoIngrediente tipo2 = new TipoIngrediente("Proteína");
            TipoIngrediente tipo3 =new TipoIngrediente("Vegetal");
            TipoIngrediente tipo4 =new TipoIngrediente("Queso");
            TipoIngrediente tipo5 =new TipoIngrediente("Salsa");
            
            tipoDAO.agregar(tipo1);
            tipoDAO.agregar(tipo2);
            tipoDAO.agregar(tipo3);
            tipoDAO.agregar(tipo4);
            tipoDAO.agregar(tipo5);
            
            
            // 2. Crear ingredientes (5 registros)
            
            Ingrediente ingrediente1 = new Ingrediente("Masa", 100, tipo1);
            Ingrediente ingrediente2 = new Ingrediente("Salsa de Tomate", 50, tipo2);
            Ingrediente ingrediente3 = new Ingrediente("Queso Mozzarella", 200, tipo3);
            Ingrediente ingrediente4 = new Ingrediente("Pepperoni", 100, tipo4);
            Ingrediente ingrediente5 = new Ingrediente("Albahaca", 50, tipo5);
            
            
            ingredienteDAO.agregar(ingrediente1);
            ingredienteDAO.agregar(ingrediente2);
            ingredienteDAO.agregar(ingrediente3);
            ingredienteDAO.agregar(ingrediente4);
            ingredienteDAO.agregar(ingrediente5);
            
            
            Producto producto1 = new Producto("Pizza Margherita", 200.0, "Pizza clásica italiana");
            Producto producto2 = new Producto("Pizza Pepperoni", 280.0, "Pizza con pepperoni");
            Producto producto3 = new Producto("Pizza Suprema", 300.0, "Pizza con todo");
            Producto producto4 = new Producto("Pizza Vegetariana", 260.0, "Pizza vegetariana");
            Producto producto5 = new Producto("Pizza Hawaiana", 270.0, "Pizza con piña");
            
            
            // 4. Crear empleados (5 registros)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            Empleado empleado1 = new Empleado("Juan Pérez", "Cajero", 123456789, sdf.parse("1990-01-15"));
            Empleado empleado2 = new Empleado("María García", "Cocinero", 987654321, sdf.parse("1985-05-20"));
            Empleado empleado3 = new Empleado("Pedro López", "Mesero", 456789123, sdf.parse("1995-08-10"));
            Empleado empleado4 = new Empleado("Ana Martínez", "Gerente", 789123456, sdf.parse("1980-03-25"));
            Empleado empleado5 = new Empleado("Luis Rodríguez", "Cocinero", 321654987, sdf.parse("1992-11-30"));
            
            empleadoDAO.agregar(empleado1);
            empleadoDAO.agregar(empleado2);
            empleadoDAO.agregar(empleado3);
            empleadoDAO.agregar(empleado4);
            empleadoDAO.agregar(empleado5);
            
            
            Venta venta1 = new Venta(sdf.parse("2024-08-15"), 500, empleado1);
            Venta venta2 = new Venta(sdf.parse("2024-08-20"), 750, empleado2);
            Venta venta3 = new Venta(sdf.parse("2024-09-01"), 1000, empleado3);
            Venta venta4 = new Venta(sdf.parse("2024-09-15"), 850, empleado4);
            Venta venta5 = new Venta(sdf.parse("2024-09-30"), 600, empleado5);
            
            ventaDAO.agregar(venta1);
            ventaDAO.agregar(venta2);
            ventaDAO.agregar(venta3);
            ventaDAO.agregar(venta4);
            ventaDAO.agregar(venta5);
            
            
            // 6. Realizar consultas
            List<Producto> productosCaros = productoDAO.buscarPrecioMayorA250();
            for (Producto p : productosCaros) {
                System.out.println(p.getNombre() + " - $" + p.getPrecio());
            }

            List<Ingrediente> ingredientesConA = ingredienteDAO.buscarIngredientesConA();
            for (Ingrediente i : ingredientesConA) {
                System.out.println(i.getNombre());
            }

            List<Venta> ventasPeriodo = ventaDAO.buscarVentasAgostoSeptiembre2024();
            for (Venta v : ventasPeriodo) {
                System.out.println("Fecha: " + sdf.format(v.getFecha()) + " - Total: $" + v.getTotal());
            }
            
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            productoDAO.cerrar();
            ingredienteDAO.cerrar();
            tipoDAO.cerrar();
            empleadoDAO.cerrar();
            ventaDAO.cerrar();
        }     
    }
}