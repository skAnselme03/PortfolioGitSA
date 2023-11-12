using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;
using ServeurBibliobazar.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ServeurBibliobazar
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();

            // Ajouts pour Visual Studio 2019
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "Bibliobazar - Andriamamonjy Camille & Stéphanie Anselme", Version = "v1" });
            });

            _ = services.AddDbContext<ska0323_bibzarContext>(options =>
            {
                options.UseMySQL(Configuration.GetConnectionString("DefaultConnection"), mySqlOptionsAction: null);
            });
            //configurer les règles de partage de ressources entre des origines différentes,
            //CORS (Cross-Origin Resource Sharing). 
            services.AddCors(options =>
            {
                options.AddPolicy("AllowReactApp", builder =>
                {
                    builder.AllowAnyOrigin()
                           .AllowAnyHeader()                    // Autorise n'importe quel en-tête dans la requête
                           .AllowAnyMethod();                   // Autorise n'importe quelle méthode HTTP (GET, POST, PUT, etc.)
                });
                /*options.AddPolicy("AllowReactApp", builder =>
                {
                    builder.WithOrigins("http://10.0.0.141:3001") // Remplacez par votre URL React
                           .AllowAnyHeader()
                           .AllowAnyMethod();
                });*/
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            //politique CORS configurée précedement pour permettre à l' API de répondre aux requêtes venant de d'une application React.
            app.UseCors("AllowReactApp");

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });

            // Nécessaire pour afficher Swagger avec VS2019
            app.UseSwagger();
            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "Bibliobazar - Andriamamonjy Camille & Stéphanie Anselme");
            });
        }
    }
}