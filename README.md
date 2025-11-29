# 🏦 BankApp — Refactor MVC con SOLID

**Fork académico — Parcial de Desarrollo en Java**

Este proyecto es una versión refactorizada del sistema **BankApp**, tomada desde un fork del repositorio original del parcial.  
El objetivo fue migrar la aplicación a una arquitectura **MVC** aplicando los principios **SOLID**, sin modificar el diseño visual original.

Permite registrar usuarios, crear cuentas, realizar transacciones y visualizar información en tablas, todo separado correctamente en **Modelos**, **Vistas** y **Controladores**.

---

## 📌 Objetivo del refactor
- Reorganizar completamente el proyecto bajo el patrón **Model–View–Controller (MVC)**.  
- Implementar validaciones y lógica de negocio desde la capa de controladores.  
- Modelos diseñados siguiendo **SOLID**.  
- Mantener intacta la interfaz gráfica original (Swing).

---

## 📁 Estructura del Proyecto

### 🧩 Modelo (Model)
- Clases de dominio bien estructuradas.  
- Simulación de almacenamiento (como el ejemplo visto en clase).  
- Responsabilidades claras siguiendo SOLID.

---

### 🖥️ Vista (View)
- Sin cambios visuales en la interfaz.  
- Componentes renombrados para mayor claridad (`btnRegistrar`, `txtIdUsuario`, etc.).  
- **Sin lógica dentro de la vista.**  
- No ejecuta la aplicación directamente (se inicia desde un `Main`).  
- Toda comunicación viaja hacia los controladores.

---

### 🎮 Controladores (Controller)
- Sistema de respuestas y códigos de estado (similar al visto en clase).  
- Manejo de:
  - Registro de usuarios  
  - Creación de cuentas  
  - Depósitos, retiros y transferencias  
  - Listado ordenado de datos  
- Lógica y validaciones centralizadas aquí.

---

## 🧠 Validaciones Implementadas

### 👤 Usuarios
- ID único  
- ID ≥ 0, máximo 9 dígitos  
- Nombre no vacío  
- Apellido no vacío  
- Edad ≥ 18  
- Listado ordenado por ID  

### 💳 Cuentas
- Solo para usuarios existentes  
- ID con formato **XXX-XXXXXX-XX**  
- Saldo inicial ≥ 0  
- Listado ordenado por ID  

### 💸 Transacciones
- Solo cuentas registradas  
- Depósitos sin límite mínimo  
- Retiros ≤ saldo disponible  
- Transferencias ≤ saldo de la cuenta origen  
- Se ordenan de la más reciente a la más antigua  

---

## 🔧 Tecnologías Utilizadas
- Java  
- Swing  
- Arquitectura MVC  
- Principios SOLID  

---

## ▶️ Ejecución
1. Clona el repositorio (tu fork): git clone https://github.com/tu_usuario/BankApp.git
2. Abre el proyecto en tu IDE.  
3. Ejecuta el archivo **Main**.  
4. La aplicación iniciará con la vista original controlada vía MVC.

---

## 🏁 Resultado Final
Un sistema bancario totalmente organizado, escalable y profesional.  

  
