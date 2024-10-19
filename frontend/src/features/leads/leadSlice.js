import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from 'axios'



export const getLeadsContent = createAsyncThunk('/leads/content', async () => {
	const response = await axios.get('http://localhost:8080/api/users/get-all', {})
	return response.data;
}
);

export const leadsSlice = createSlice({
    name: 'leads',
    initialState: {
        isLoading: false,
        leads : []
    },
    reducers: {


        addNewLead: (state, action) => {
            let {newLeadObj} = action.payload
            state.leads = [...state.leads, newLeadObj]
        },

        deleteLead: (state, action) => {
            let {index} = action.payload
            state.leads.splice(index, 1)
        },
        updateLead: (state, action) => {
            const { userId, updatedLeadObj } = action.payload;
            const index = state.leads.findIndex(lead => lead.userId === userId); 
            if (index !== -1) {
                state.leads[index] = updatedLeadObj; 
            }
        }
    },

    extraReducers: {
		[getLeadsContent.pending]: state => {
			state.isLoading = true
		},
		[getLeadsContent.fulfilled]: (state, action) => {
			state.leads = action.payload
			state.isLoading = false
		},
		[getLeadsContent.rejected]: state => {
			state.isLoading = false
		},
    }
})

export const { addNewLead, deleteLead,updateLead } = leadsSlice.actions

export default leadsSlice.reducer