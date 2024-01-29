import { useEffect, useState } from 'react'

const RecipeFeed = ({ recipeFunc }) => {

    const [recipeBank, setRecipeBank] = useState([]);

    const recipeBankHandler = () => {
        setRecipeBank(recipeFunc())
    }

    useEffect(() => {
        if (recipeBank.length === 0) {
            recipeBankHandler();
        }
    })

    return (
        <div>RecipeFeed</div>
    )
}

export default RecipeFeed
