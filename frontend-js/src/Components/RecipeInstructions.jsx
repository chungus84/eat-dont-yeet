import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

const RecipeInstructions = ({ recipeFun }) => {

    const recipeId = useParams();

    const [recipe, setRecipe] = useState({});

    const navigate = useNavigate();


    const recipeInstructionsHandler = async recipeId => {
        setRecipe(await recipeFun(recipeId));
    }

    useEffect(() => {
        if (Object.keys(recipe).length === 0) {
            recipeInstructionsHandler(recipeId.id);
        }

    }, [])

    return (
        <>
            <button className="btn btn-primary" onClick={(() => navigate("/recipe/all"))}>Find Recipes</button>
            <br />
            <h2>{recipe.title} Instructions</h2>
            <img src={recipe.image} alt={recipe.title} />
            <h3>directions</h3>
            <p>{recipe.instructions}</p>
            <p>Ready in: {recipe.readyInMinutes} mins</p>
            <h3>Dietary & Allergies</h3>
            {recipe.dairyFree && (
                <p>Dairy Free</p>
            )}
            {recipe.glutenFree && (
                <p>Gluten Free</p>
            )}
            {recipe.vegan && (
                <p>Vegan</p>
            )}
            {recipe.vegetarian && (
                <p>Vegetarian</p>
            )}
        </>

    )


}

export default RecipeInstructions
