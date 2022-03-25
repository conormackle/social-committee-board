import { get, post, put } from 'axios'

const baseUrl = 'http://ec2-63-34-220-104.eu-west-1.compute.amazonaws.com'

export const getAll = (controller) => get(`${baseUrl}/${controller}/getAll`)
export const getById = (controller, id) => get(`${baseUrl}/${controller}/getById/${id}`)
export const create = (controller, body) => post(`${baseUrl}/${controller}/create`, body)
export const update = (controller, id, body) => put(`${baseUrl}/${controller}/update/${id}`, body)
