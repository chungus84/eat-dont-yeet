import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import RecipeCard from './utils/RecipeCard';

import { getCurrentUser } from './utils/helper';

const RecipeFeed = ({ recipeFunc }) => {

    const [recipeBank, setRecipeBank] = useState([]);

    const user = getCurrentUser();
    const navigate = useNavigate();

    const recipeBankHandler = async () => {
        setRecipeBank(await recipeFunc())
    }

    const populateRecipeCards = array => {

        if (array?.length > 0) {
            const displayRecipe = [];
            displayRecipe.push(array.map(recipe => {
                return <RecipeCard data={recipe} key={recipe.id} />
            }))
            return displayRecipe;
        }

    }

    useEffect(() => {

        if (recipeBank.length === 0) {
            recipeBankHandler();
        }
    }, [])

    // console.log(recipeBank);

    return (
        <>
            <h2>RecipeFeed</h2>
            <button className='btn btn-primary' onClick={(() => { navigate(`/profile/${user.userId}`) })}>Back to profile</button>
            <div className="row row-cols-sm-auto justify-content-around feed">
                {populateRecipeCards(recipeBank)}
            </div>



        </>


    )
}

export default RecipeFeed
