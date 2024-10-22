import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from 'axios'



export const getTransactionsContent = createAsyncThunk('/transactions/content', async () => {
	const response = await axios.get('http://localhost:8080/api/purchase/get-all', {})
	return response.data.reverse();
}
);

export const transactionsSlice = createSlice({
    name: 'transactions',
    initialState: {
        isLoading: false,
        transactions : []
    },
    reducers: {
    },

    extraReducers: {
		[getTransactionsContent.pending]: state => {
			state.isLoading = true
		},
		[getTransactionsContent.fulfilled]: (state, action) => {
			state.transactions = action.payload
			state.isLoading = false
		},
		[getTransactionsContent.rejected]: state => {
			state.isLoading = false
		},
    }
})

export default transactionsSlice.reducer