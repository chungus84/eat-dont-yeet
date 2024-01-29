import { useNavigate } from "react-router-dom";


const RecipeCard = ({ data }) => {

    const navigate = useNavigate();



    const popIngredients = (ingredients) => {
        let count = 0;
        const ingredientsList = []
        ingredientsList.push(ingredients.map(ingredient => {
            return <li key={count++}>{ingredient}</li>
        }))
        return ingredientsList;
    }


    return (
        <div className="card col-lg-10 col-sm-10 col-xs-10" onClick={(() => navigate(`/recipe/${data.recipeId}`))}>
            <div className="cardHeader">Name: {data.title}</div>
            <div className="card-body">
                <img src={data.image} alt={data.title} />
                <ul>
                    {popIngredients(data.ingredients)}
                </ul>

            </div>
        </div>
    )
}

export default RecipeCard
