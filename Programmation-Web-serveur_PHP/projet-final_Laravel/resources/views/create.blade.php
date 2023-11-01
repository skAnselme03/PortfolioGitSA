@php
    $currentRoute = \Request::route()->getName();
@endphp

@extends('layouts.default')

@section('content')
    <div class="container">
        <h2>Publier un nouveau lien</h2>
        <form action="{{ route('link.post') }}" method="POST">
            @csrf

            <div class="mb-3">
                <label for="link">Lien</label>
                <input type="text" name="link" id="link" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="description">Description</label>
                <textarea name="description" id="description" class="form-control" rows="4" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Publier</button>
        </form>
    </div>
@endsection
