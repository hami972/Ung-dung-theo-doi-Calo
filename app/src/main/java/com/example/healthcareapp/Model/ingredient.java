package com.example.healthcareapp.Model;

public class ingredient {
    String idIngredient, nameIngredient;
    String calorieIngredient;
    public ingredient () {

    }
    public ingredient(String idIngredient, String nameIngredient, String calorieIngredient) {
        this.calorieIngredient = calorieIngredient;
        this.idIngredient = idIngredient;
        this.nameIngredient = nameIngredient;
    }
    public String getCalorieIngredient() {
        return calorieIngredient;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setCalorieIngredient(String calorieIngredient) {
        this.calorieIngredient = calorieIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }
}
